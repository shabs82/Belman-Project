/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal.DAO;

import belmanfinalsemester.be.Department;
import belmanfinalsemester.be.Order;
import belmanfinalsemester.dal.DBConnector;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author kulsoom-Abbas
 */
public class EventLogDAO {
    
    private DBConnector connector;

public EventLogDAO() throws FileNotFoundException {
    
    this.connector = new DBConnector();
    }
public void logEvent(Order order , Department dep , String logData , LocalDate currentDate) throws SQLException{
    try (Connection con = connector.getConnection()) {
            String EventLog = " Insert into EventLog  (Order_ID, Dept_ID, Event, Date) values (?, ?, ?, ?)  ";
            PreparedStatement stmt = con.prepareStatement(EventLog);
            stmt.setString(1, order.getOrderNumber());
            stmt.setInt(2, dep.getDeptID());
            stmt.setString(3, logData);
            stmt.setDate(4, java.sql.Date.valueOf(currentDate));
            stmt.execute();
   } catch (SQLException ex){
       ex.printStackTrace();
   }
}
}   

    
    

