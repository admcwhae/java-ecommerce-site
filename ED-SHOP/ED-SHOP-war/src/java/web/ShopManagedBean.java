/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.ProductDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import session.ProductCatalogBeanRemote;

/**
 *
 * @author amcwhae
 */
@Named(value = "shopManagedBean")
@RequestScoped
public class ShopManagedBean implements Serializable {

    @EJB
    private ProductCatalogBeanRemote productCatalogBean;

    private List<ProductDTO> products;
    private String string;

    /**
     * Creates a new instance of ShopManagedBean
     */
    public ShopManagedBean() {

    }

    @PostConstruct
    public void init() {
        this.products = productCatalogBean.getProducts();
    }

    public List<ProductDTO> getProducts() {
        return products;
    }
    
    public String getString() {
        return string;
    }
    
    public boolean increaseString() {
        this.string = "\n" + string;
        return true;
    }
}
