/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal;

import belmanfinalsemester.be.Order;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author MockDALManager
 */
public class MockDALManager {
    private List<Order> orderInfo;
    
    public List<Order> createOrders (){
        Order o1 = new Order();
        o1.setOrderNumber(500-100-200-01);
        o1.setStartDate("2019-05-08");
        o1.setEndDate("2019-05-13");
        o1.setTimeLeft(3); 
        
        Order o2 = new Order();
        o2.setOrderNumber(500-100-200-02);
        o2.setStartDate("2019-05-10");
        o2.setEndDate("2019-05-15");
        o2.setTimeLeft(2); 

    
         ArrayList<Order> list1 = new ArrayList();
         list1.add(o1);
         list1.add(o2);
         return list1;
  }   
}
