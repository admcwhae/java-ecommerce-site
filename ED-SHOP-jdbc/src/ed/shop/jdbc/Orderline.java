/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.shop.jdbc;

/**
 *
 * @author amcwhae
 */
public class Orderline {
    private int id;
    private int productId; 
    private int orderId;
    private int quantity;

    public Orderline(int id, int productId, int orderId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
