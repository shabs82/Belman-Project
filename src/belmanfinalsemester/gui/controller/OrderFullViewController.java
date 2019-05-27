/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

import belmanfinalsemester.be.Department;
import belmanfinalsemester.be.Order;
import belmanfinalsemester.gui.model.MainModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Test
 */
public class OrderFullViewController implements Initializable {

    private MainModel model;
    private Order order;
    
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
 
    public void injectModel(MainModel model)
    {
        this.model = model;
    }
    
    public void setOrderInfo(Order currentOrder){  
     this.order = currentOrder;
     lblOrderNum.setText(currentOrder.getOrderNumber());
     lblStartDate.setText(currentOrder.getStartDate().toString());
     lblDeliveryDate.setText(currentOrder.getEndDate().toString());
     lblLeftDate.setText(Integer.toString(currentOrder.getTimeLeft()));
     lblCustomer.setText(currentOrder.getCustomerName());
     progressBar.setProgress(currentOrder.getProgress() );
    
    }

    @FXML
    private void clickFinish(ActionEvent event) {
        model.submitTask(order);
    }
 }

