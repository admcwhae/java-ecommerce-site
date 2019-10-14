/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Orders;
import entity.OrdersDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amcwhae
 */
@Stateless
public class OrdersFacade implements OrdersFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "ED-SHOP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Orders orders) {
        em.persist(orders);
    }

    private void edit(Orders orders) {
        em.merge(orders);
    }

    private void remove(Orders orders) {
        em.remove(em.merge(orders));
    }

    private Orders find(int id) {
        return em.find(Orders.class, id);
    }

    @Override
    public boolean createRecord(OrdersDTO ordersDTO) {
        if (find(ordersDTO.getId()) != null) {
            return false;
        }
        try {
            Orders orders = this.DTO2DAO(ordersDTO);
            this.create(orders);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private Orders DTO2DAO(OrdersDTO ordersDTO) {
        Orders orders = new Orders();
        orders.setId(ordersDTO.getId());
        orders.setName(ordersDTO.getName());
        orders.setCastle(ordersDTO.getCastle());
        orders.setKingdom(ordersDTO.getKingdom());
        orders.setStatus(ordersDTO.getStatus());
        orders.setDate(ordersDTO.getDate());
        
        return orders;
     
    }
  
    private OrdersDTO DAO2DTO(Orders orders) {
        OrdersDTO ordersDTO = new OrdersDTO(
                orders.getId(),
                orders.getName(),
                orders.getCastle(),
                orders.getKingdom(),
                orders.getStatus(),
                orders.getDate());

        return ordersDTO;
    }

}
