/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

//import belmanfinalsemester.BelmanException;
import belmanfinalsemester.be.Order;
import belmanfinalsemester.exception.BelmanException;
import belmanfinalsemester.gui.model.MainModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wailampoon
 */
public class MainViewController implements Initializable {

    private MainModel mModel = new MainModel();
    
    @FXML
    private Label currentDate;
    @FXML
    private Label currentTime;
    @FXML
    private JFXComboBox<String> combobox;
    @FXML
    private Label currentweekday;
    @FXML
    private BorderPane MainBorderPane;
    @FXML
    private JFXTextField txtFieldSearchBar;
    @FXML
    private TableColumn<Order, Integer> clmOrderNum;
    @FXML
    private TableColumn<Order, String> clmStartDate;
    @FXML
    private TableColumn<Order, String> clmEndDate;
    @FXML
    private TableColumn<Order, Integer> clmTimeLeft;
    @FXML
       
     TableView<Order> tvOrders;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeComboBox();
        setDateAndTime();
        //loadTableViewFXML();
        
        setTableColumn();
    }
    
    private void initializeComboBox()
    {
        combobox.getItems().add("Halvfab");
        combobox.getItems().add("Bælg");
        combobox.getItems().add("Montage 1");
        combobox.getItems().add("Montage 2");
        
    }

    public void setDateAndTime() {
        SimpleDateFormat forDate = new SimpleDateFormat("YYYY/MM/dd");
        SimpleDateFormat forTime = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat forDayOfWeek = new SimpleDateFormat("EEEE");
        Date dateForDate = new Date();

        currentDate.setText(forDate.format(dateForDate));
        currentTime.setText(forTime.format(dateForDate));
        currentweekday.setText(forDayOfWeek.format(dateForDate));

    }
    private void setTableColumn(){
       clmOrderNum.setCellValueFactory(new PropertyValueFactory("orderNumber"));
       clmStartDate.setCellValueFactory(new PropertyValueFactory("startDate"));
       clmEndDate.setCellValueFactory(new PropertyValueFactory("endDate"));
       clmTimeLeft.setCellValueFactory(new PropertyValueFactory("timeLeft"));
    
    }
    
    
//   public void setOrdersTable(String departmentName){
//        tvOrders.setItems(mModel.getOrders(departmentName));
//   }
//    
//        private void loadTableViewFXML() {
//        try
//        {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/belmanfinalsemester/gui/view/OrderTableView.fxml"));
//            Parent root = fxmlLoader.load();
//            MainBorderPane.setCenter(root);
//        }
//        catch(IOException ex)
//        {
//            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void dropDown(ActionEvent event) throws BelmanException {
        String selectedDepartment = combobox.getSelectionModel().getSelectedItem();
        if(combobox.getSelectionModel().getSelectedItem() != null)
        {
            tvOrders.setItems(mModel.getOrders(selectedDepartment));
        }
    }

    @FXML
    private void showOrderFullView(MouseEvent event) {
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

    @FXML
    private void searchOrders(KeyEvent event) {
        tvOrders.setItems(mModel.searchOrders(txtFieldSearchBar.getText()));
    }
}
