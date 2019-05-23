/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.controller;

//import belmanfinalsemester.BelmanException;
import belmanfinalsemester.be.Department;
import belmanfinalsemester.be.Order;
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
import java.util.List;

import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

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
import javafx.scene.image.Image;
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

    private ScheduledExecutorService executor;
    private List<Department> depList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeComboBox();
        setDateAndTime();
        executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(() -> initializeThreading(), 0, 1, TimeUnit.SECONDS);
        //loadTableViewFXML();

        setTableColumn();
    }

    private void initializeComboBox() {
        try {
            depList = mModel.getDepartments();
        } catch (SQLException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Department department : depList) {
            combobox.getItems().add(department.getDeptName());
        }

    }

    private void initializeThreading() {
        Platform.runLater(() -> {
            setDateAndTime();
        });
    }

    public void setDateAndTime() {
        SimpleDateFormat forDate = new SimpleDateFormat("YYYY/MM/dd");
        SimpleDateFormat forTime = new SimpleDateFormat("HH:mm:ss aa");
        SimpleDateFormat forDayOfWeek = new SimpleDateFormat("EEEE");
        Date dateForDate = new Date();

        currentDate.setText(forDate.format(dateForDate));
        currentTime.setText(forTime.format(dateForDate));
        currentweekday.setText(forDayOfWeek.format(dateForDate));

    }

    private void setTableColumn() {
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
    private void dropDown(ActionEvent event) throws BelmanException, SQLException {
        int selectedDepartment = combobox.getSelectionModel().getSelectedIndex();

        if (combobox.getSelectionModel().getSelectedItem() != null) {
            tvOrders.setItems(mModel.getOrders(depList.get(selectedDepartment)));
        } else {
            MessageBoxHelper.displayError("Please select your respective Department");
        }
    }

    @FXML
    private void showOrderFullView(MouseEvent event) {

        if (event.getClickCount() == 2) {

            try {
                Parent root;  //FXMLLoader.load(getClass().getResource("/belmanfinalsemester/gui/view/OrderFullView.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/belmanfinalsemester/gui/view/OrderFullView.fxml"));
                root = (Parent) fxmlLoader.load();
                OrderFullViewController controller = fxmlLoader.getController();
                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(scene);

                stage.getIcons().add(new Image("/resources/images/belman_logo_retina.png"));
                stage.setTitle("Belman");

                stage.show();

                Order order = tvOrders.getSelectionModel().getSelectedItem();
                controller.setOrderInfo(order);

            } catch (IOException ex) {
                Logger.getLogger(OrderFullViewController.class.getName()).log(Level.SEVERE, null, ex);
                //ex.printStackTrace();
            }
        }
    }

    @FXML
    private void searchOrders(KeyEvent event) {
        if (combobox.getSelectionModel().getSelectedItem() == null) {
            MessageBoxHelper.displayError("Select the right Department first.");
            txtFieldSearchBar.clear();
        }
        if (combobox.getSelectionModel().getSelectedItem() != null && txtFieldSearchBar.getText() != null) {
            if (!txtFieldSearchBar.getText().matches("[0.-9.]*")
                    && txtFieldSearchBar.getText().matches("^[a-zA-Z]*$")) {
                MessageBoxHelper.displayError("Search by Order Number.");
                txtFieldSearchBar.clear();
            } else {
                tvOrders.setItems(mModel.searchOrders(txtFieldSearchBar.getText()));
            }

//
//           else if(!txtFieldSearchBar.getText().matches("[0.-9.]*") && 
//                        txtFieldSearchBar.getText().matches("^[a-zA-Z]*$")){
//                 MessageBoxHelper.displayError("Search by Order Number.");
//                 txtFieldSearchBar.clear();
        }
    }
}
