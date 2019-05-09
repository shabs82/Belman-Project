/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kulsoom-Abbas
 */
public class DepartmentTasks {
    
    private DBConnector connector;
     
 public DepartmentTasks(DBConnector connector) {
        this.connector = connector;
    }
 public void addDepartmentTasks(String name , Date date,String order) throws Exception{
    
    try (Connection con = connector.getConnection()) {
            String sql = "INSERT INTO Department (Department_Name,Start_Date,End_Date,Finished_Order) VALUES(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDate(2,date);
            stmt.setDate(3, date);
            stmt.setString(4, order);
            stmt.executeUpdate();

        }
        catch(SQLException ex)
        {
            throw new Exception("Cannot connect to the database");
        }
    
}
}

    

