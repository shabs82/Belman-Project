/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.model;

import belmanfinalsemester.be.Department;
import belmanfinalsemester.be.Order;
import belmanfinalsemester.bll.Facade;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Test
 */
public class MainModel {
    private Facade facade = new Facade();
    private ObservableList<Order> obList = FXCollections.observableArrayList();
    private ObservableList<Order> filteredList = FXCollections.observableArrayList();
    
    public ObservableList<Order> getOrders(Department departmentName){
        List<Order> orders = facade.getOrders(departmentName);
        obList.setAll(orders);
        return obList;
    }
    
    public ObservableList<Order> searchOrders(String key)
    {
        filteredList.setAll(facade.searchOrders(obList, key));
        return filteredList;
    }
    
    public List<Department> getDepartments() throws SQLException{
        return facade.getDepartments();
    }
}
