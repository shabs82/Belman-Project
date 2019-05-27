/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kulsoom-Abbas
 */
public class DatabaseWriter {

    private DBConnector connector;

    public DatabaseWriter() throws FileNotFoundException {
        this.connector = new DBConnector();
    }

    public void addDepartment(String deptName) {
        try (Connection con = connector.getConnection()) {
            String sql = "Declare @countint int "
                    + "SELECT @countint = COUNT (*) "
                    + "FROM [dbo].[Department] AS D "
                    + "WHERE D.[Dept_Name] = ? "
                    + "if (@countint <1) "
                    + "Begin "
                    + "Insert Into Department(Dept_Name) values (?) "
                    + "End ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, deptName);
            stmt.setString(2, deptName);
            stmt.execute();
        } catch (SQLException ex) {

            System.out.println(ex);
        }

    }

    public void addProductionOrder(String orderID, Date deliveryDate, Boolean finishedOrder, String custName) throws Exception {

        try (Connection con = connector.getConnection()) {
            String sql = "Declare @countint int "
                    + " SELECT @countint = COUNT (*) "
                    + " FROM [dbo].[Order] AS O "
                    + " WHERE O.[Order_ID]= ? "
                    + " if (@countint <1) "
                    + " Begin "
                    + " INSERT INTO [dbo].[Order] (Order_Id,Delivery_Date,Finished_Order) VALUES(?,?,?) "
                    + " End ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, orderID);
            stmt.setString(2, orderID);
            stmt.setDate(3, deliveryDate);
            stmt.setBoolean(4, false);
            stmt.execute();

        } catch (SQLException ex) {

            System.out.println(ex);
            //throw new Exception("Cannot connect to the database");
        }
        addCustomerOrder(orderID, custName);

    }

    public void addCustomerOrder(String orderID, String custName) throws SQLException {
        try (Connection con = connector.getConnection()) {
            String sqlcreate = " Select * from Customer "
                    + " where Customer_Name = ? ";
            PreparedStatement stmt = con.prepareStatement(sqlcreate);
            System.out.println(custName);
            stmt.setString(1, custName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int custID = rs.getInt("Customer_ID");
                String sql = "Declare @countint int "
                        + " SELECT @countint = COUNT (*) "
                        + " FROM [dbo].[Customer_Order] AS CO "
                        + " WHERE CO.Order_ID = ? AND CO.[Customer_ID]= ? "
                        + " if (@countint <1) "
                        + " Begin "
                        + " INSERT INTO  Customer_Order(Order_ID, Customer_ID) VALUES(?, ?) "
                        + " End ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, orderID);
                ps.setInt(2, custID);
                ps.setString(3, orderID);
                ps.setInt(4, custID);
                ps.execute();
            }
        } catch (SQLServerException ex) {

            System.out.println(ex);
        }
    }

    public void addAvailableWorkers(String initials, String name, long salary) throws Exception {
        try (Connection con = connector.getConnection()) {
            String sql = "Declare @countint int "
                    + " SELECT @countint = COUNT (*) "
                    + " FROM [dbo].[Employee] AS E "
                    + " WHERE E.[Emp_Name]= ? "
                    + " if (@countint <1) "
                    + " Begin "
                    + " INSERT INTO Employee (Emp_Initials,Emp_Name,Emp_Salary) VALUES(?, ?,?) "
                    + " End ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, initials);
            stmt.setString(3, name);
            stmt.setLong(4, salary);
            stmt.execute();

        } catch (SQLException ex) {

            System.out.println(ex);
            //throw new Exception("Cannot connect to the database");
        }

    }

    public void addCustomer(String custName) throws SQLServerException {
        try (Connection con = connector.getConnection()) {
            String sql = "Declare @countint int "
                    + "SELECT @countint = COUNT (*) "
                    + "FROM [dbo].[Customer] AS C "
                    + "WHERE C.[Customer_Name] = ? "
                    + "if (@countint <1) "
                    + "Begin "
                    + "Insert Into Customer(Customer_Name) values (?) "
                    + "End ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, custName);
            stmt.setString(2, custName);
            stmt.execute();

        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }

    public void addDepartmentOrder(String Dept_ID, Date Start_Date, Date End_Date, Boolean Finished_Order, String Order_ID) throws Exception {

        try (Connection con = connector.getConnection()) {
            String sql = "Declare @countint int "
                    + " SELECT @countint = COUNT (*) "
                    + " FROM [dbo].[Department_Order]  AS DO "
                    + " WHERE DO.[Dept_ID]= ? AND DO.Order_ID = ? "
                    + " if (@countint <1) "
                    + " Begin "
                    + " INSERT INTO Department_Order (Dept_ID,Start_Date,End_Date,Finished_Order,Order_ID) VALUES(?, ?, ?, ?, ?)\n"
                    + " End ";
            PreparedStatement stmt = con.prepareStatement(sql);

            int depID = getIntOfDepartment(con, Dept_ID);

            stmt.setInt(1, depID);
            stmt.setString(2, Order_ID);
            stmt.setInt(3, depID);
            stmt.setDate(4, Start_Date);
            stmt.setDate(5, End_Date);
            stmt.setBoolean(6, Finished_Order);
            stmt.setString(7, Order_ID);
            stmt.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
            //throw new Exception("Cannot connect to the database");
        }

    }

    private int getIntOfDepartment(Connection con, String name) throws SQLException {
        String sql1 = "Select * FROM Department WHERE Dept_Name = ?";
        PreparedStatement sttmt = con.prepareStatement(sql1);
        sttmt.setString(1, name);
        ResultSet rs = sttmt.executeQuery();
        rs.next();
        return rs.getInt("Dept_ID");
    }

}
