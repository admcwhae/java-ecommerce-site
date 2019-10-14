/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Orderline;
import entity.OrderlineDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amcwhae
 */
@Stateless
public class OrderlineFacade implements OrderlineFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "ED-SHOP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Orderline orderline) {
        em.persist(orderline);
    }

    private void edit(Orderline orderline) {
        em.merge(orderline);
    }

    private void remove(Orderline orderline) {
        em.remove(em.merge(orderline));
    }

    private Orderline find(int id) {
        return em.find(Orderline.class, id);
    }

    @Override
    public boolean createRecord(OrderlineDTO orderlineDTO) {
        if (find(orderlineDTO.getId()) != null) {
            return false;
        }
        try {
            Orderline orderline = this.DTO2DAO(orderlineDTO);
            this.create(orderline);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private Orderline DTO2DAO(OrderlineDTO orderlineDTO) {
        Orderline orderline = new Orderline();
        orderline.setId(orderlineDTO.getId());
//        orderline.setProductId(orderlineDTO.getProductId());
//        orderline.setOrdersId(orderlineDTO.getOrdersId());
        orderline.setQuantity(orderlineDTO.getQuantity());

        return orderline;

    }

    private OrderlineDTO DAO2DTO(Orderline orderline) {
        OrderlineDTO orderlineDTO = new OrderlineDTO(
                orderline.getId(),
                orderline.getProductId().getId(),
                orderline.getOrdersId().getId(),
                orderline.getQuantity()
        );

        return orderlineDTO;
    }
}
