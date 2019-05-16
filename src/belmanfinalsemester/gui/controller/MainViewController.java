/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

//import belmanfinalsemester.BelmanException;
import belmanfinalsemester.exception.BelmanException;
import belmanfinalsemester.gui.model.MainModel;
import belmanfinalsemester.gui.util.MessageBoxHelper;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeComboBox();
        setDateAndTime();
        loadTableViewFXML();
    }
    
    private void initializeComboBox()
    {
        combobox.getItems().add("Halvfab");
        combobox.getItems().add("Bælg");
        combobox.getItems().add("Montage 1");
        combobox.getItems().add("Montage 2");
        
        txtFieldSearchBar.textProperty().addListener((Observable observable) -> {
            try {
                reload();
            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
    
        private void loadTableViewFXML() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/belmanfinalsemester/gui/view/OrderTableView.fxml"));
            Parent root = fxmlLoader.load();
            MainBorderPane.setCenter(root);
        }
        catch(IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void dropDown(ActionEvent event) throws BelmanException {
        String selectedDepartment = combobox.getSelectionModel().getSelectedItem();
        if(combobox.getSelectionModel().getSelectedItem() != null)
        {
            setOrdersTable(selectedDepartment);
        }
        

    }

    public void setOrdersTable(String selectedDepartment) {

        try 
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/belmanfinalsemester/gui/view/OrderTableView.fxml"));
            Parent root = fxmlLoader.load();
            
            OrderTableViewController controller = fxmlLoader.getController();
            controller.setOrdersTable(selectedDepartment);
            MainBorderPane.setCenter(root);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void reload() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
                                            ("/belmanfinalsemester/gui/view/OrderTableView.fxml"));
            Parent root = fxmlLoader.load();
            
            OrderTableViewController controller = fxmlLoader.getController();
            controller.tvOrders.setItems(mModel.getOrders(txtFieldSearchBar.getText()));
        } catch (IOException ex) {
            MessageBoxHelper.displayError("File not found");
        }
    }

    @FXML
    private void searchOrders(KeyEvent event) {
        System.out.println("123");
    }
}
