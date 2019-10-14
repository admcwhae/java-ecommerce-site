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
public class ProductDB {

    public Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        String url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        String username = "APP";
        String password = "APP";
        return DriverManager.getConnection(url, username, password);
    }

    public void createProductTable() {

        Statement stmnt = null;
        Connection cnnct = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE PRODUCT ("
                    + "ID INT NOT NULL, "
                    + "NAME VARCHAR(50), "
                    + "THUMBNAIL VARCHAR(100), "
                    + "IMAGE VARCHAR(100), "
                    + "DESCRIPTION VARCHAR(255), "
                    + "PRICE_WOOD INT, "
                    + "PRICE_GOLD INT, "
                    + "QUANTITY INT, "
                    + "CONSTRAINT PK_PRODUCT PRIMARY KEY (ID))");
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

    public void dropProductTable() {
        {
            Connection cnnct = null;
            Statement stmnt = null;
            try {
                cnnct = getConnection();
                stmnt = cnnct.createStatement();
                stmnt.execute("DROP TABLE PRODUCT");
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

    public void addRecords(ArrayList<Product> products) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "INSERT INTO PRODUCT VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            for (Product product : products) {
                pStmnt.setInt(1, product.getId());
                pStmnt.setString(2, product.getName());
                pStmnt.setString(3, product.getThumbnail());
                pStmnt.setString(4, product.getImage());
                pStmnt.setString(5, product.getDescription());
                pStmnt.setInt(6, product.getPriceWood());
                pStmnt.setInt(7, product.getPriceGold());
                pStmnt.setInt(8, product.getQuantity());
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
