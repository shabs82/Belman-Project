/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal;

import belmanfinalsemester.be.Department;
import belmanfinalsemester.be.Order;
import belmanfinalsemester.dal.DAO.DepartmentDAO;
import belmanfinalsemester.dal.DAO.OrderDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MockDALManager
 */
public class DALManager {

    private List<Order> orderInfo;
    OrderDAO orderDAO;
    DepartmentDAO depDAO;
    

    public DALManager() {
        try {
            this.orderDAO = new OrderDAO();
            this.depDAO = new DepartmentDAO();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DALManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Order> getAllOrders(Department departmentName, LocalDate currentDate) {

        List<Order> currentOrders = orderDAO.getAllOrdersFromDept(departmentName, currentDate);
        return currentOrders;
    }

    public List<Department> getDepartments() throws SQLException {
        return depDAO.getDepartment();
    }

    public List<Order> getOrders(Department departmentName, LocalDate currentDate) {
        List<Order> currentOrders = orderDAO.getOrders(departmentName, currentDate);
        return currentOrders;
    }
    
     public void submitTask(Department dep, Order order) throws SQLServerException, SQLException {
         orderDAO.submitTask(dep, order);
     }
}
