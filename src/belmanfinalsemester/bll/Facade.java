/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.bll;

import belmanfinalsemester.be.Order;
import belmanfinalsemester.dal.MockDALManager;
import java.util.List;

/**
 *
 * @author Test
 */
public class Facade {
    
    MockDALManager mcDalManager = new MockDALManager();
    public List<Order> createOrders (){
        return mcDalManager.createOrders();
    } 
    
}
