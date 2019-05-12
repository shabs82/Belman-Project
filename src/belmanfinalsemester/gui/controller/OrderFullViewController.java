/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

import belmanfinalsemester.be.Order;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Test
 */
public class OrderFullViewController implements Initializable {

    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label lblEmployees;
    @FXML
    private JFXButton btnfinish;
    @FXML
    private Label lblStartDate;
    @FXML
    private Label lblDeliveryDate;
    @FXML
    private Label lblCustomer;
    @FXML
    private Label lblOrderNum;
    @FXML
    private Label lblLeftDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    
 
    
    
    public void setOrderInfo(Order currentOrder){   
     lblOrderNum.setText(currentOrder.getOrderNumber());
     lblStartDate.setText(currentOrder.getStartDate());
     lblDeliveryDate.setText(currentOrder.getEndDate());
     lblLeftDate.setText(currentOrder.getTimeLeft());
     lblCustomer.setText(currentOrder.getCustomerName());
    
    }
}
