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
public class OrderDB {

    public Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        String url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        String username = "APP";
        String password = "APP";
        return DriverManager.getConnection(url, username, password);
    }

    public void createOrderTable() {

        Statement stmnt = null;
        Connection cnnct = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE ORDERS ("
                    + "ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                    + "NAME VARCHAR(50), "
                    + "CASTLE VARCHAR(50), "
                    + "KINGDOM VARCHAR(50), "
                    + "STATUS VARCHAR(10), "
                    + "DATE DATE, "
                    + "CONSTRAINT PK_ORDER PRIMARY KEY (ID))");
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

    public void dropOrderTable() {
        {
            Connection cnnct = null;
            Statement stmnt = null;
            try {
                cnnct = getConnection();
                stmnt = cnnct.createStatement();
                stmnt.execute("DROP TABLE ORDERS");
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

    public void addRecords(ArrayList<Order> orders) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "INSERT INTO ORDERS(NAME, CASTLE, KINGDOM, DATE) VALUES (?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            for (Order order : orders) {
                pStmnt.setString(1, order.getName());
                pStmnt.setString(2, order.getCastle());
                pStmnt.setString(3, order.getKingdom());
                pStmnt.setDate(4, order.getDate());
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
