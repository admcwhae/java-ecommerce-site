/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CartItemDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author amcwhae
 */
@Remote
public interface ShopCartBeanRemote {
//    public boolean add(CartItemDTO cartItem);
    
    public boolean addCartItem(CartItemDTO cartItem);
    
    public boolean deleteCartItem(int productId);
    
    public boolean updateCartItem(CartItemDTO cartItem);
    
    public ArrayList<CartItemDTO> getCart();

    public boolean addItem(int productId);

    public int getGoldTotal();

    public int getWoodTotal();

    public boolean calculateTotals();

    public boolean emptyCart();
}
