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
public class OrderlineDB {
    public Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        String url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        String username = "APP";
        String password = "APP";
        return DriverManager.getConnection(url, username, password);
    }

    public void createOrderlineTable() {

        Statement stmnt = null;
        Connection cnnct = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE ORDERLINE ( "
                    + "ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                    + "PRODUCT_ID INT CONSTRAINT FK_ORDERLINE_PRODUCT REFERENCES PRODUCT(ID), "
                    + "ORDERS_ID INT CONSTRAINT FK_ORDERLINE_ORDERS REFERENCES ORDERS(ID), "
                    + "QUANTITY INT, "
                    + "CONSTRAINT PK_ORDERLINE PRIMARY KEY (ID))");
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
    
    public void dropOrderlineTable() {
        {
            Connection cnnct = null;
            Statement stmnt = null;
            try {
                cnnct = getConnection();
                stmnt = cnnct.createStatement();
                stmnt.execute("DROP TABLE ORDERLINE");
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

    public void addRecords(ArrayList<Orderline> orderlines) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "INSERT INTO ORDERLINE(PRODUCT_ID, ORDERS_ID, QUANTITY) VALUES (?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            for (Orderline orderline : orderlines) {
                pStmnt.setInt(1, orderline.getProductId());
                pStmnt.setInt(2, orderline.getOrderId());
                pStmnt.setInt(3, orderline.getQuantity());
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
