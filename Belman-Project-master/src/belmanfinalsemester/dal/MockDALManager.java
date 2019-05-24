/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal;

import belmanfinalsemester.be.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MockDALManager
 */
public class MockDALManager {
    private List<Order> orderInfo;
    
    public List<Order> getOrders(){
        Order o1 = new Order();
        o1.setOrderNumber("500-100-200-01");
        o1.setStartDate(LocalDate.now().minusDays(5));
        o1.setEndDate(LocalDate.now().plusDays(3));
        o1.setTimeLeft(3); 
        o1.setCustomerName("Christiano Ronaldo");
        
        
        Order o2 = new Order();
        o2.setOrderNumber("500-100-200-02");
        o2.setStartDate(LocalDate.now().minusDays(7));
        o2.setEndDate(LocalDate.now().plusDays(2));
        o2.setTimeLeft(2);
        o2.setCustomerName("David Beckham");

         ArrayList<Order> list1 = new ArrayList();
         list1.add(o1);
         list1.add(o2);
         return list1;
  }   
}
