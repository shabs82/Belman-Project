/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal.DAO;

import belmanfinalsemester.be.Department;
import belmanfinalsemester.dal.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kulsoom-Abbas
 */
public class DepartmentDAO {

    private DBConnector connector;

    public DepartmentDAO() throws FileNotFoundException {

        this.connector = new DBConnector();

    }

    public List<Department> getDepartment() throws SQLServerException, SQLException {
        List<Department> allDepartment = new ArrayList();
        try (Connection con = connector.getConnection()) {

            String Department = "SELECT D.[Dept_Name], D.[DEPT_ID] "
                    + "FROM [dbo].[Department] AS D ";
            PreparedStatement stmt = con.prepareStatement(Department);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                allDepartment.add(new Department(rs.getInt("Dept_ID"), rs.getString("Dept_Name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allDepartment;
    }

}
