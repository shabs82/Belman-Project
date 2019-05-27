/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.model;

import belmanfinalsemester.be.Department;
import belmanfinalsemester.be.Order;
import belmanfinalsemester.bll.Facade;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Test
 */
public class MainModel {
    private Facade facade = new Facade();
    private Department selectedDepartment;
    private ObservableList<Order> obList = FXCollections.observableArrayList();
    private ObservableList<Order> filteredList = FXCollections.observableArrayList();
    
    public MainModel()
    {
        runOrderObserver();
    }
    
    public void setDepartment(Department d)
    {
        this.selectedDepartment = d;
    }
    
    public ObservableList<Order> getOrders(){
        List<Order> orders = facade.getOrders(selectedDepartment);
        obList.setAll(orders);
        return obList;
    }
    
    private void runOrderObserver()
    {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> updateOrders(), 5, 5, TimeUnit.SECONDS);   
    }
    
    private void updateOrders()
    {
        if(selectedDepartment != null)
        {
            List<Order> updatedOrders = facade.getOrders(selectedDepartment);
            obList.setAll(updatedOrders);
        }
    }
    
    public ObservableList<Order> searchOrders(String key)
    {
        filteredList.setAll(facade.searchOrders(obList, key));
        return filteredList;
    }
    
    public List<Department> getDepartments() throws SQLException{
        return facade.getDepartments();
    }
    
     public void submitTask(Order order) throws SQLServerException, SQLException {
         facade.submitTask(selectedDepartment, order);
     }
}
