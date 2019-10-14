/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author amcwhae
 */
public class CartItemDTO implements Serializable {

    private int productId;
    private String name;
    private int priceWood;
    private int priceGold;
    private int quantity;

    public CartItemDTO(int productId, String name, int priceWood, int priceGold, int quantity) {
        this.productId = productId;
        this.name = name;
        this.priceWood = priceWood;
        this.priceGold = priceGold;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriceWood() {
        return priceWood;
    }

    public void setPriceWood(int priceWood) {
        this.priceWood = priceWood;
    }

    public int getPriceGold() {
        return priceGold;
    }

    public void setPriceGold(int priceGold) {
        this.priceGold = priceGold;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null) {
            return result;
        }
        if (object instanceof CartItemDTO) {
            CartItemDTO other = (CartItemDTO) object;
            if (this.productId == other.productId) {
                result = true;
            }
        }
        return result;
    }
}
