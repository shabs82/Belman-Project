/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

import belmanfinalsemester.SomeException;
import belmanfinalsemester.be.Order;
import belmanfinalsemester.gui.model.MainModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    @FXML
    private Button btn1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      setTableColumn();
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

    @FXML
    private void displayOrderInfo(ActionEvent event) throws SomeException, IOException {
        Stage stage = null;
        Order selectedOrder = tableView.getSelectionModel().getSelectedItem();
        if (selectedOrder == null){
            throw new SomeException("At least one order must be selected");
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("/belmanfinalsemester/gui/view/OrderInfo.fxml")); 
            Scene scene = new Scene(root);
            stage = (Stage) btn1.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
     }
        
   }
    
}
