/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kulsoom-Abbas
 */
public class DepartmentTasks {
    
    private DBConnector connector;
     
 public DepartmentTasks() {
        try {
            connector = new DBConnector();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DepartmentTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void addDepartmentTasks(String Dept_ID , Date Start_Date, Date End_Date,Boolean Finished_Order, String Order_ID) throws Exception{
    
    try (Connection con = connector.getConnection()) {
            String sql = "INSERT INTO Department_Order (Dept_ID,Start_Date,End_Date,Finished_Order,Order_ID) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Dept_ID);
            stmt.setDate(2,Start_Date);
            stmt.setDate(3, End_Date);
            stmt.setBoolean(4, Finished_Order);
            stmt.setString(5,Order_ID );
            stmt.execute();

        }
        catch(SQLException ex)
        {
            throw new Exception("Cannot connect to the database");
        }
    
}
}

    

