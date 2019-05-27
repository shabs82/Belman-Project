/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal;

import JSON.JSONReader;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private JSONReader jR;

    //String ExcelFileToConver = null;
    public DatabaseFileWatcher() throws IOException {

        this.dbWriter = new DatabaseWriter();
        this.connector = new DBConnector();
        this.jR = new JSONReader();

        this.watcher = FileSystems.getDefault().newWatchService();

        this.dir = Paths.get("src/dataFolder");

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

                if (Files.probeContentType(child).equals("text/plain")) {
                    try {
                        //JSON
                        jR.readJsonFile(fileCaught.getPath());
                    } catch (Exception ex) {
                        Logger.getLogger(DatabaseFileWatcher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
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
