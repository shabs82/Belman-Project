/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester;

import JSON.JSONReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Test
 */
public class BelmanFinalSemester extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/belmanfinalsemester/gui/view/Main.fxml"));
        
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/belman_logo_retina.png"));
        stage.setTitle("Belman");
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        JSONReader jr = new JSONReader();
        jr.readJsonFile();
        launch(args);
    }
    
}
