/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CartItemDTO;
import entity.ProductDTO;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author amcwhae
 */
@Stateful
public class ShopCartBean implements ShopCartBeanRemote {
    @EJB
    ProductFacadeLocal productFacade;
    

    private ArrayList<CartItemDTO> cart = new ArrayList<>();
    private int woodTotal;
    private int goldTotal;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public ShopCartBean() {
//        if (cart != null) {
//            cart = new ArrayList<>();
//            CartItemDTO item = new CartItemDTO(5, "TestItem", 500, 500, 2);
//            cart.add(item);
//        }
    }

//    @Override
//    public boolean add(CartItemDTO cartItem) {
//        boolean result = false; 
//        try {
//            result = cart.add(cartItem);
//        } catch (Exception e) {
//            
//        }
//        return result;
//    }
    @Override
    public boolean addCartItem(CartItemDTO cartItem) {
        boolean result = false;
        try {
            for (CartItemDTO item : cart) {
                if (item.equals(cartItem)) {
                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                    result = true;
                }
            }
            if (!result && cartItem != null) {
                result = cart.add(cartItem);
            }
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public boolean addItem(int productId) {
        ProductDTO product = productFacade.getRecord(productId);
        CartItemDTO cartItem = new CartItemDTO(product.getId(), product.getName(), product.getPriceWood(), product.getPriceGold(), 1);        
        boolean result = false;
        try {
            for (CartItemDTO item : cart) {
                if (item.equals(cartItem)) {
                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                    result = true;
                }
            }
            if (!result && cartItem != null) {
                result = cart.add(cartItem);
            }
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public boolean deleteCartItem(int productId) {
        boolean result = false;

        try {
            for (CartItemDTO item : cart) {
                if (item.getProductId() == productId) {
                    cart.remove(item);
                    result = true;
                }
            }
        } catch (Exception ex) {

        }
        return result;
    }

    @Override
    public boolean updateCartItem(CartItemDTO cartItem) {
        boolean result = false;

        try {
            for (CartItemDTO item : cart) {
                if (cartItem.equals(item)) {
                    cart.remove(item);
                    cart.add(cartItem);
                    result = true;
                }
            }
        } catch (Exception ex) {

        }
        return result;
    }
    
    @Override
    public boolean calculateTotals() {
        boolean result = false; 
        this.woodTotal = 0;
        this.goldTotal = 0;
        for(CartItemDTO cartItem: cart) {
            this.woodTotal += cartItem.getPriceWood() * cartItem.getQuantity();
            this.goldTotal += cartItem.getPriceGold() * cartItem.getQuantity();
        }
        
        return result;
    }
    
    @Override 
    public boolean emptyCart() {
        boolean result = false;
        
        this.cart = new ArrayList<>();
        result = true;
        return result;
    }

    @Override
    public ArrayList<CartItemDTO> getCart() {
        return this.cart;
    }

    @Override
    public int getWoodTotal() {
        return woodTotal;
    }

    @Override
    public int getGoldTotal() {
        return goldTotal;
    }

    @Remove
    public void remove() {
        cart = null;
    }
}
