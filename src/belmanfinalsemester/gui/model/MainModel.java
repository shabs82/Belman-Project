/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.gui.model;

import belmanfinalsemester.be.Order;
import belmanfinalsemester.bll.Facade;
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
    
    public List<Order> createOrders (){
        return facade.createOrders();
    }
}
