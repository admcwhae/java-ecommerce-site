/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.OrderlineDTO;
import javax.ejb.Local;

/**
 *
 * @author amcwhae
 */
@Local
public interface OrderlineFacadeLocal {

    public boolean createRecord(OrderlineDTO orderlineDTO);
    
}
