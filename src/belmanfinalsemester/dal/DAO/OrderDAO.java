/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal.DAO;

import belmanfinalsemester.be.Department;
import belmanfinalsemester.be.Order;
import belmanfinalsemester.dal.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kulsoom-Abbas
 */
public class OrderDAO {

    private DBConnector connector;

    public OrderDAO() throws FileNotFoundException {
        this.connector = new DBConnector();
    }

    public List<Order> getOrders(Department dep, LocalDate currentDate) {
        List<Order> allOrders = new ArrayList();
        try (Connection con = connector.getConnection()) {
            String Orders = " SELECT O.[Order_ID],O.[Delivery_Date],O.[Finished_Order],D.[Dept_Name],C.[Customer_Name],DO.[Start_Date],DO.End_Date "
                    + "    FROM [dbo].[Order] AS O "
                    + "    JOIN [dbo].[Department_Order] AS DO "
                    + "    ON DO.Order_ID = O.Order_ID "
                    + "    JOIN [dbo].[Department] AS D "
                    + "    ON D.Dept_ID = DO.Dept_ID "
                    + "    JOIN [dbo].[Customer_Order] AS CO "
                    + "    ON CO.Order_ID = DO.Order_ID "
                    + "    JOIN [dbo].[Customer] AS C "
                    + "    ON c.[Customer_ID] = CO.Customer_ID "
                    + "    WHERE DO.Dept_Id = ? AND DO.Finished_Order = 0 AND DO.Start_Date <= ? ";
            PreparedStatement stmt = con.prepareStatement(Orders);
            stmt.setInt(1, dep.getDeptID());
            stmt.setDate(2, java.sql.Date.valueOf(currentDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order newOrder = new Order(rs.getString("Order_ID"), convertToLD(rs.getDate("Start_Date")), convertToLD(rs.getDate("End_Date")), rs.getString("Customer_Name"));
                allOrders.add(newOrder);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return allOrders;
    }

    private LocalDate convertToLD(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public List<Order> getAllOrdersFromDept(Department dep, LocalDate currentDate) {
        List<Order> allOrders = new ArrayList();
        String sql = "SELECT O.[Order_ID],O.[Delivery_Date],O.[Finished_Order],D.[Dept_Name],C.[Customer_Name],DO.[Start_Date],DO.End_Date "
                + "    FROM [dbo].[Order] AS O "
                + "    JOIN [dbo].[Department_Order] AS DO "
                + "    ON DO.Order_ID = O.Order_ID "
                + "    JOIN [dbo].[Department] AS D "
                + "    ON D.Dept_ID = DO.Dept_ID "
                + "    JOIN [dbo].[Customer_Order] AS CO "
                + "    ON CO.Order_ID = DO.Order_ID "
                + "    JOIN [dbo].[Customer] AS C "
                + "    ON c.[Customer_ID] = CO.Customer_ID "
                + "    WHERE O.Order_ID IN (SELECT O.Order_ID "
                + "	                    FROM [dbo].[Department_Order] "
                + "			    WHERE [Dept_ID]= ? AND [Finished_Order] = 0 AND [Start_Date]<= ?) "
                + "	AND DO.[Finished_Order]= 0 "
                + "	ORDER BY [Order_ID] ,[Start_Date] ";
        try (Connection con = connector.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, dep.getDeptID());
            stmt.setDate(2, java.sql.Date.valueOf(currentDate));
            ResultSet rs = stmt.executeQuery();
            String CurrentOrder = "";
            while (rs.next()) {
                if (!CurrentOrder.equals(rs.getString("Order_ID"))) {
                    Order newOrder = new Order(rs.getString("Order_ID"), convertToLD(rs.getDate("Start_Date")), convertToLD(rs.getDate("End_Date")), rs.getString("Customer_Name"));
                    allOrders.add(newOrder);
                    CurrentOrder = rs.getString("Order_ID");//each time a new order appears dont duplicate order.
                }
            }
        } catch (SQLException ex) {
            //throw new Exception("Cannot connect to the database");
        }
        return allOrders;

    }

    public void submitTask(Department dep, Order order) throws SQLServerException, SQLException {
        String sql = "UPDATE   [Department_Order] "
                + " SET  [Finished_Order] = 1 "
                + " WHERE [Department_Order].[Dept_ID] = ? AND [Department_Order].[Order_ID]= ? "
                + " IF( "
                + "SELECT  COUNT([Order_ID]) AS TasksLeft "
                + " FROM  [dbo].[Department_Order] AS O "
                + " WHERE O.[Finished_Order] = 0 AND O.[Order_ID]=?"
                + " "
                + " ) = 0 "
                + " BEGIN "
                + " UPDATE [Order]"
                + " SET  [Finished_Order] = 1"
                + " WHERE [Order].[Order_ID] = ? "
                + " END ";
        try (Connection con = connector.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, dep.getDeptID());
            stmt.setString(2, order.getOrderNumber());
            stmt.setString(3, order.getOrderNumber());
            stmt.setString(4, order.getOrderNumber());
            stmt.execute();
        }
    }

}