/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

//import belmanfinalsemester.SomeException;
import belmanfinalsemester.be.Order;
import belmanfinalsemester.gui.model.MainModel;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author wailampoon
 */
public class MainController implements Initializable {

    @FXML
    private Label currentDate;
    @FXML
    private Label currentTime;
    @FXML
    private JFXComboBox combobox;
    @FXML
    private Label currentweekday;
   
    

    private MainModel mModel = new MainModel();
    @FXML
    private BorderPane MainBorderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox.getItems().add("Halvfab");
        combobox.getItems().add("Bælg");
        combobox.getItems().add("Montage 1");
        combobox.getItems().add("Montage 2");
        setDateAndTime();
        try {
            setTableViewFXML();

        
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

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

    @FXML
    // private void dropDown(ActionEvent event) throws SomeException {
    private void dropDown(ActionEvent event) {
        String orderValue = "";
        if (combobox.getItems() == null) {
            // throw new SomeException ("You need to specify your department");
        }

        int selectedIndex = combobox.getSelectionModel().getSelectedIndex();
        switch (selectedIndex) {

            case 0:
                orderValue = mModel.createOrders().toString();
                break;
        }
        

    }
//        System.out.println(combobox.getSelectionModel().getSelectedItem().toString());

    public void setTableViewFXML() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/belmanfinalsemester/gui/view/TableView.fxml"));
        MainBorderPane.setCenter(root);
    }

}
