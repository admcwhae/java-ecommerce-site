/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Product;
import entity.ProductDTO;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amcwhae
 */
@Stateless
public class ProductFacade implements ProductFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "ED-SHOP-ejbPU")
    private EntityManager em;

    public ProductFacade() {
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Product product) {
        em.persist(product);
    }

    private void edit(Product product) {
        em.merge(product);
    }

    private void remove(Product product) {
        em.remove(em.merge(product));
    }

    private Product find(Object id) {
        return em.find(Product.class, id);
    }

    @Override
    public ArrayList<ProductDTO> findAll() {
        ArrayList<Product> products = new ArrayList<>(em.createNamedQuery("Product.findAll").getResultList());
        ArrayList<ProductDTO> returnList = new ArrayList<>();
        
        products.forEach((product) -> {
            returnList.add(DAO2DTO(product));
        });
        
        return returnList;
    }
    
    @Override
    public ProductDTO getRecord(int id) {
        Product product = (em.find(Product.class, id));
        return DAO2DTO(product);
    }

    @Override
    public boolean createRecord(ProductDTO productDTO) {
        if (find(productDTO.getId()) != null) {
            return false;
        }
        try {
            Product product = this.DTO2DAO(productDTO);
            this.create(product);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateRecord(ProductDTO productDTO) {
        boolean result = false;
        if (find(productDTO.getId()) != null) {
            Product product = DTO2DAO(productDTO);
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
            result = true;
        }
        return result;
    }
    
    @Override 
    public String hasRecord(int productId) {
    String result = "Null";
    Product product = find(productId);
        if (product != null) {
            result = product.getName();
        }
        return result;
    }
    
    @Override
    public String testReturn() {
        return "String returned";
    }

    private Product DTO2DAO(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setThumbnail(productDTO.getThumbnail());
        product.setPriceGold(productDTO.getPriceGold());
        product.setPriceWood(productDTO.getPriceWood());
        product.setQuantity(productDTO.getQuantity());

        return product;
    }

    private ProductDTO DAO2DTO(Product product) {
        ProductDTO productDTO = new ProductDTO(
                product.getId(),
                product.getName(),
                product.getThumbnail(),
                product.getImage(),
                product.getDescription(),
                product.getPriceWood(),
                product.getPriceGold(),
                product.getQuantity());

        return productDTO;
    }

}
