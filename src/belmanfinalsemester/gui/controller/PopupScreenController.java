/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

import com.jfoenix.controls.JFXProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Test
 */
public class PopupScreenController implements Initializable {

    @FXML
    private Label lblCustomer;
    @FXML
    private Label lblStartDate;
    @FXML
    private Label lblDeliveryDate;
    @FXML
    private Label lblOrderNumber;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label lblEmployees;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
