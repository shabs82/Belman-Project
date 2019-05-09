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
import java.time.LocalDate;

/**
 *
 * @author kulsoom-Abbas
 */

public class AvailableWorkers {
     private DBConnector connector;
 
    public  AvailableWorkers() throws FileNotFoundException{
        connector = new DBConnector();
    }
    public void addAvailableWorkers(String initials ,String name,long salary) throws Exception {
        try (Connection con = connector.getConnection()) {
            String sql = "INSERT INTO Employee (Employee_Initials,Employee_Name,Employee_Salary) VALUES(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, initials);
            stmt.setString(2,name);
            stmt.setLong(3,salary);
            stmt.execute();

        }
        catch(SQLException ex)
        {
            throw new Exception("Cannot connect to the database");
        }
    
}
}