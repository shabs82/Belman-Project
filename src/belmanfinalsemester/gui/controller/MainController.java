/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

import belmanfinalsemester.be.Order;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

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
        combobox.getItems().add("Halvfab");
        combobox.getItems().add("Bælg");
        combobox.getItems().add("Montage 1");
        combobox.getItems().add("Montage 2");
        setDateAndTime();
    }

    public void setDateAndTime() {
        SimpleDateFormat forDate = new SimpleDateFormat("YYYY/MM/dd");
        SimpleDateFormat forTime = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat forDayOfWeek = new SimpleDateFormat("EEEE");
        Date dateForDate = new Date();

        currentDate.setText(forDate.format(dateForDate));
        currentTime.setText(forTime.format(dateForDate));
        currentweekday.setText(forDayOfWeek.format(dateForDate));
        
        clmOrderNum.setCellValueFactory(new PropertyValueFactory("Order Number"));
        clmStartDate.setCellValueFactory(new PropertyValueFactory("Start Date"));
        clmEndDate.setCellValueFactory(new PropertyValueFactory("End Date"));
        clmTimeLeft.setCellValueFactory(new PropertyValueFactory("Time Left"));

    }

    @FXML
    private void dropDown(ActionEvent event) {
     //   if (combobox.)
    }

}
