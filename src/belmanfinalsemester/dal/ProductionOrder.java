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
public class ProductionOrder {
    
    private DBConnector connector;
     
 public ProductionOrder(DBConnector connector) {
        this.connector = connector;
    }
 public void addProductionOrder(String name , Date time) throws Exception{
    
    try (Connection con = connector.getConnection()) {
            String sql = "INSERT INTO ORDER (Customer_Name,Delivery_Time) VALUES(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDate(2,time);
            stmt.executeUpdate();

        }
        catch(SQLException ex)
        {
            throw new Exception("Cannot connect to the database");
        }
    
}
}
