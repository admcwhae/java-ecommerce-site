/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ProductDTO;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author amcwhae
 */
@Local 
public interface ProductFacadeLocal {
    public ArrayList<ProductDTO> findAll();
    public boolean createRecord(ProductDTO productDTO);
    public boolean updateRecord(ProductDTO productDTO);

    public String hasRecord(int productId);

    public String testReturn();

    public ProductDTO getRecord(int id);
}
