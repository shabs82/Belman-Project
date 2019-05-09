/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

import belmanfinalsemester.be.Order;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author wailampoon
 */
public class TableViewController implements Initializable {

    @FXML
    private TableColumn<Order, Integer> clmOrderNum;
    @FXML
     private TableColumn<Order, String> clmStartDate;
    @FXML
    private TableColumn<Order, String> clmEndDate;
    @FXML
   private TableColumn<Order, Integer> clmTimeLeft;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        clmOrderNum.setCellValueFactory(new PropertyValueFactory("Order Number"));
        clmStartDate.setCellValueFactory(new PropertyValueFactory("Start Date"));
       clmEndDate.setCellValueFactory(new PropertyValueFactory("End Date"));
       clmTimeLeft.setCellValueFactory(new PropertyValueFactory("Time Left"));
    }    
    
}
