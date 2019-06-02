/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.bll;

import belmanfinalsemester.be.Department;
import belmanfinalsemester.be.Order;
import belmanfinalsemester.dal.DALManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Test
 */
public class Facade {
    
   // MockDALManager mcDalManager = new MockDALManager();
    DALManager dalManager = new DALManager();
    
    public List<Order> getOrders (Department departmentName){
        LocalDate currentDate = LocalDate.now();
        List<Order> orders =  dalManager.getOrders(departmentName,currentDate);
        for(Order o : orders){
            double progress = calculateProgress(o);
            o.setProgress(progress);
        }
        return orders;
    } 
    
    private double calculateProgress(Order order){
        if(LocalDate.now().isAfter(order.getEndDate())){
            return 1;
        }
        else if(LocalDate.now().isBefore(order.getStartDate())){
            return 0;
        }
        else{
            double currentDays = ChronoUnit.DAYS.between(order.getStartDate(), LocalDate.now());
            double totalDays = ChronoUnit.DAYS.between(order.getStartDate(), order.getEndDate());
            return currentDays/totalDays;
        }
    }
    
    public List<Order> searchOrders(List<Order> allOrders, String key){
        List<Order> filteredList = new ArrayList();
        for(Order order: allOrders){
            if(order.getOrderNumber().substring(0, key.length()).equals(key)){
                filteredList.add(order);
            }
        }
        return filteredList;
    }
    
    public List<Department> getDepartments() throws SQLException{
        return dalManager.getDepartments();
    }
    
     public void submitTask(Department dep, Order order) throws SQLServerException, SQLException {
         dalManager.submitTask(dep, order, LocalDate.now());
     }
}
