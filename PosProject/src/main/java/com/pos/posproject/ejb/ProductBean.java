/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.ejb;

import com.pos.posproject.common.ProductDetails;
import com.pos.posproject.entity.Product;
import com.pos.posproject.entity.ProductCatalog;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Monica
 */
@Stateless
public class ProductBean {

    private static final Logger LOG = Logger.getLogger(ProductBean.class.getName());
    @PersistenceContext
    private EntityManager em;
    
    
    public ProductDetails findById(Integer productId) {
        Product product = em.find(Product.class, productId);
        return new ProductDetails(product.getId(), product.getBarcode(), product.getName(),product.getDescription(), product.getPrice(), product.getCategory(), product.getProductCatalog().getProductCatalogName());
   
    
    }
    
    public List<ProductDetails> getAllProducts() {
        LOG.info("getAllProducts");
        try {
            Query query = em.createQuery("SELECT c FROM Product c");
            List<Product> products = (List<Product>) query.getResultList();
            return copyProductsToDetails(products);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
    private List<ProductDetails> copyProductsToDetails(List<Product> products) {
        List<ProductDetails> detailsList = new ArrayList<>();
        for (Product product : products) {
            ProductDetails productDetails = new ProductDetails(product.getId(), 
                    product.getBarcode(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getProductCatalog().getProductCatalogName());
            detailsList.add(productDetails);
        }
        return detailsList;
    }
    
    public void createProduct(String barcode, String name, String description, Double price, String category, int productCatalog) {
        LOG.info("createProduct");
        Product product = new Product();
        product.setBarcode(barcode);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
       
        
        ProductCatalog productCatalogId = em.find(ProductCatalog.class, productCatalog);
        productCatalogId.getProducts().add(product);
        product.setProductCatalog(productCatalogId);
        
        em.persist(product);
    }

    public void updateProduct(int productId, String barcode, String name, String description, Double price, String category, Integer productCatalogId) {

        LOG.info("updateProduct");
        Product product = em.find(Product.class, productId);
        product.setBarcode(barcode);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);

        ProductCatalog oldProductCatalog = product.getProductCatalog();
        oldProductCatalog.getProducts().remove(product);

        ProductCatalog productCatalog = em.find(ProductCatalog.class, productCatalogId);
        productCatalog.getProducts().add(product);
        product.setProductCatalog(productCatalog);
    }

    public void deleteProductsByIds(List<Integer> ids) {
        LOG.info("deleteProductsByIds");
        for (Integer id : ids) {
            Product product = em.find(Product.class, id);
            em.remove(product);
        }
    }
    
}
