/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.shop.jdbc;

import java.sql.Date;

/**
 *
 * @author amcwhae
 */
public class Order {
    private int id;
    private String name;
    private String castle;
    private String kingdom; 
    private String status;
    private Date date;

    public Order(int id, String name, String castle, String kingdom, String status, Date date) {
        this.id = id;
        this.name = name;
        this.castle = castle;
        this.kingdom = kingdom;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCastle() {
        return castle;
    }

    public void setCastle(String castle) {
        this.castle = castle;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
