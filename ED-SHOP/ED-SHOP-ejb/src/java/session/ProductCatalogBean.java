/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ProductDTO;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author amcwhae
 */
@Stateless
public class ProductCatalogBean implements ProductCatalogBeanRemote {
    @Resource
    SessionContext context;
    
    @EJB
    private ProductFacadeLocal productFacade;
    
    private ArrayList<ProductDTO> products;
    
    
    public ProductCatalogBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.products = productFacade.findAll();
        
    }
    
    @Override
    public ArrayList<ProductDTO> getProducts() {
        return this.products;
    }
}
