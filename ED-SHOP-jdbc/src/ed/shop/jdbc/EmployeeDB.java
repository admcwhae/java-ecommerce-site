/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.shop.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author amcwhae
 */
public class EmployeeDB {

    public Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        String url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        String username = "APP";
        String password = "APP";
        return DriverManager.getConnection(url, username, password);
    }

    public void createEmployeeTable() {

        Statement stmnt = null;
        Connection cnnct = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE EMPLOYEE ( "
                    + "ID VARCHAR(10), "
                    + "NAME VARCHAR(50), "
                    + "PASSWORD CHAR(64), "
                    + "ROLE VARCHAR(15), "
                    + "CONSTRAINT PK_EMPLOYEE PRIMARY KEY (ID))");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }

    }

    public void dropEmployeeTable() {
        {
            Connection cnnct = null;
            Statement stmnt = null;
            try {
                cnnct = getConnection();
                stmnt = cnnct.createStatement();
                stmnt.execute("DROP TABLE EMPLOYEE");
            } catch (SQLException ex) {
                while (ex != null) {
                    ex.printStackTrace();
                    ex = ex.getNextException();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (stmnt != null) {
                    try {
                        stmnt.close();
                    } catch (SQLException e) {
                    }
                }
                if (cnnct != null) {
                    try {
                        cnnct.close();
                    } catch (SQLException sqlEx) {
                    }
                }
            }
        }
    }

    public void addRecords(ArrayList<Employee> employees) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            for (Employee employee : employees) {
                pStmnt.setString(1, employee.getId());
                pStmnt.setString(2, employee.getName());
                pStmnt.setString(3, employee.getPassword());
                pStmnt.setString(4, employee.getRole());
                int rowCount = pStmnt.executeUpdate();
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }
}
