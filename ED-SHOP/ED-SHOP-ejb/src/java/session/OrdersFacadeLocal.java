/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.OrdersDTO;
import javax.ejb.Local;

/**
 *
 * @author amcwhae
 */
@Local
public interface OrdersFacadeLocal {

    public boolean createRecord(OrdersDTO ordersDTO);
    
}
