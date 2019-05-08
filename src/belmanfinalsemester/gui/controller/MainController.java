/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setDateAndTime();
    }

    public void setDateAndTime() {
        SimpleDateFormat forDate = new SimpleDateFormat("YYYY/MM/dd");
        SimpleDateFormat forTime = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat forDayOfWeek = new SimpleDateFormat("EEEE");
        Date dateForDate = new Date();

        currentDate.setText(forDate.format(dateForDate));
        currentTime.setText(forTime.format(dateForDate));

    }

}
