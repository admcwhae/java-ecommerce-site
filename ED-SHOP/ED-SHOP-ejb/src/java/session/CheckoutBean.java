/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.OrdersDTO;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author amcwhae
 */
@Stateful
public class CheckoutBean implements CheckoutBeanRemote {
    @EJB
    OrdersFacadeLocal ordersFacadeLocal;
    @EJB
    OrderlineFacadeLocal orderlineFacadeLocal;
    
    public boolean completeOrder() {
        boolean result = false;
        OrdersDTO ordersDTO = new OrdersDTO(1, "Alex", "Sample Castle", "Testvaria", "processing", new Date());
        if (ordersFacadeLocal.createRecord(ordersDTO)) {
            result = true;
        }
        return result;
    }
}
