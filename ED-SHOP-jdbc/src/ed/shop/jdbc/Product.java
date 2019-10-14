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
public class Product {
    private int id;
    private String name;
    private String thumbnail;
    private String image;
    private String description;
    private int priceWood;
    private int priceGold;
    private int quantity;

    public Product(int id, String name, String thumbnail, String image, String description, int priceWood, int priceGold, int quantity) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.image = image;
        this.description = description;
        this.priceWood = priceWood;
        this.priceGold = priceGold;
        this.quantity = quantity;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
