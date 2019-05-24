/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import org.json.simple.parser.ParseException;

/**
 *
 * @author kulsoom-Abbas
 */
public class DatabaseFileWatcher {
    
    private DBConnector connector;  

    //Watcher
    private final WatchService watcher;

    //private final Map<WatchKey, Path> keys;
    private Path dir;
    private Path targetDirPath;
    private Path invalidDirPath;
    private DatabaseWriter dbWriter;
    
    

    //String ExcelFileToConver = null;

    public DatabaseFileWatcher() throws IOException {
    
        this.dbWriter = new DatabaseWriter();
        this.connector = new DBConnector();
        //this.dbWriter = new DatabaseWriter(connector);
        //this.excelC = new ExcelConverter();

        this.watcher = FileSystems.getDefault().newWatchService();

        this.dir = Paths.get("src/dataTransfers");
        this.targetDirPath = Paths.get("src/dataTransfers/InsertedValidFiles");
        this.invalidDirPath = Paths.get("src/dataTransfers/InvalidFiles");

        try {
            WatchKey key = dir.register(watcher, ENTRY_CREATE);
            processEvents2();
        } catch (ParseException | IOException ex) {
            System.out.println(ex);
        }
    }

    void processEvents2() throws IOException, ParseException {
        while (true) {

            // wait for key to be signaled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                // This key is registered only
                // for ENTRY_CREATE events,
                // but an OVERFLOW event can
                // occur regardless if events
                // are lost or discarded.
                if (kind == OVERFLOW) {
                    continue;
                }

                // The filename is the
                // context of the event.
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path filename = ev.context();
// Verify that the new
                //  file is a text file.
                // Resolve the filename against the directory.
                // If the filename is "test" and the directory is "foo",
                // the resolved name is "test/foo".
                Path child = dir.resolve(filename);
                // here is a file or a folder modified in the folder
                File fileCaught = child.toFile();
                Path sourceFilePath = Paths.get(fileCaught.toURI());
                Path endDirPath = targetDirPath;

                if (Files.probeContentType(child) == null) {
                    endDirPath = invalidDirPath;
                } else if (Files.probeContentType(child).equals("text/plain")) { //JSON
                    dbWriter.checkForChangesInFile(1, fileCaught.getPath());
                } else if (Files.probeContentType(child).equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) { //Excel
                    //String newPathForFile = excelC.csvConverter(fileCaught.getPath()); // Get new generated CSV file path
                    //Path excelsourceFilePath = Paths.get(fileCaught.getPath()); // Set old file for deletion
                    //Files.delete(excelsourceFilePath);  //Delete old Excel file

                    //dbWriter.checkForChangesInFile(2, newPathForFile); // Write CSV to database
                    //sourceFilePath = Paths.get(newPathForFile); //Set new CSV pathway for transfering to storage
                    filename = sourceFilePath.getFileName(); // Get new CSV file name
                } else if (Files.probeContentType(child).equals("application/vnd.ms-excel")) { //CSV
                    dbWriter.checkForChangesInFile(2, fileCaught.getPath());
                } else {
                    endDirPath = invalidDirPath;
                }
                Path targetFilePath = endDirPath.resolve(filename);
                Files.move(sourceFilePath, targetFilePath);
System.out.println("Transfer done for " + filename);
            }

            // Reset the key -- this step is critical if you want to
            // receive further watch events.  If the key is no longer valid,
            // the directory is inaccessible so exit the loop.
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }

    
}
    

