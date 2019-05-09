/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

import belmanfinalsemester.be.Order;
import belmanfinalsemester.gui.model.MainModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML
    private TableView<Order> tableView;

    
     private MainModel mModel = new MainModel();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
      setTableColumn();
     // setItem();
    }   
    
    
    private void setTableColumn(){
     clmOrderNum.setCellValueFactory(new PropertyValueFactory("orderNumber"));
     clmStartDate.setCellValueFactory(new PropertyValueFactory("startDate"));
       clmEndDate.setCellValueFactory(new PropertyValueFactory("endDate"));
       clmTimeLeft.setCellValueFactory(new PropertyValueFactory("timeLeft"));
    
    }
    
    
   public void setOrdersTable(String departmentName){
        tableView.setItems(mModel.getOrders(departmentName));
   
   }
    
}
