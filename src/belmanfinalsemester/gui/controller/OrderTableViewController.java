/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

import belmanfinalsemester.exception.BelmanException;
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
import belmanfinalsemester.gui.util.MessageBoxHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author wailampoon
 */
public class OrderTableViewController implements Initializable {

    @FXML
    private TableColumn<Order, Integer> clmOrderNum;
    @FXML
    private TableColumn<Order, String> clmStartDate;
    @FXML
    private TableColumn<Order, String> clmEndDate;
    @FXML
    private TableColumn<Order, Integer> clmTimeLeft;
    @FXML
    private TableView<Order> tvOrders;

    
     private MainModel mModel = new MainModel();
    

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
        tvOrders.setItems(mModel.getOrders(departmentName));
   }

    @FXML
    private void showOrderFullView(MouseEvent event) 
    {
        if(event.getClickCount() == 2)
        {
             
            try 
            {
                Parent root;  //FXMLLoader.load(getClass().getResource("/belmanfinalsemester/gui/view/OrderFullView.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/belmanfinalsemester/gui/view/OrderFullView.fxml"));
                root = (Parent) fxmlLoader.load();
                OrderFullViewController controller = fxmlLoader.getController();
                Scene scene = new Scene(root);
                
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                
               Order order = tvOrders.getSelectionModel().getSelectedItem();         
               controller.setOrderInfo(order);
                
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(OrderTableViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
