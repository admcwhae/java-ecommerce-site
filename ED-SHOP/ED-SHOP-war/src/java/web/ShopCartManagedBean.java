/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.CartItemDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import session.CheckoutBeanRemote;
import session.ShopCartBeanRemote;

/**
 *
 * @author amcwhae
 */
@Named(value = "shopCartManagedBean")
@SessionScoped
public class ShopCartManagedBean implements Serializable {
    @EJB
    private ShopCartBeanRemote shopCartBean;
    @EJB
    private CheckoutBeanRemote checkoutBean;
    
    /**
     * Creates a new instance of ShopCartManagedBean
     */
    public ShopCartManagedBean() {
    }
    
    public ArrayList<CartItemDTO> getCart() {
        return shopCartBean.getCart();
    }
    
    
    public String addItem(int productId) {
        if(shopCartBean.addItem(productId)) {
            return "checkout.xhtml";
        }
        else {
            return null;
        } 
    }
    
    public String removeItem(int productId) {
        if(shopCartBean.deleteCartItem(productId)) {
            return "checkout.xhtml";
        }
        else {
            return null;
        } 
    }
    
    public String emptyCart() {
        if(shopCartBean.emptyCart()) {
            return "checkout.xhtml";
        }
        else {
            return null;
        } 
    }
    
    public String checkout() {
        if (checkoutBean.completeOrder())
        {
                    
            emptyCart();
            return "storepage.xhtml";
        
        }
        else return "checkout.xhtml";
    }
    
    public int getWoodTotal() {
        shopCartBean.calculateTotals();
        return shopCartBean.getWoodTotal();
    }
    
    public int getGoldTotal() {
        shopCartBean.calculateTotals();
        return shopCartBean.getGoldTotal();
    }
    
}
