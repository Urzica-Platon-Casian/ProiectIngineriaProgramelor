/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.ejb;

import com.pos.posproject.common.ProductCatalogDetails;
import com.pos.posproject.common.ProductDetails;
import com.pos.posproject.entity.Product;
import com.pos.posproject.entity.ProductCatalog;
import java.util.ArrayList;
import java.util.Collection;
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
public class ProductCatalogBean {

    private static final Logger LOG = Logger.getLogger(ProductCatalogBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    public List<ProductCatalogDetails> getAllProductCatalogs() {
        LOG.info("getAllProductCatalogs");
        try {
            Query query = em.createQuery("SELECT u FROM ProductCatalog u");
            List<ProductCatalog> productCatalogs = (List<ProductCatalog>) query.getResultList();
            return copyProductCatalogsToDetails(productCatalogs);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<ProductCatalogDetails> copyProductCatalogsToDetails(List<ProductCatalog> productCatalogs) {
        List<ProductCatalogDetails> detailsProductatalogList = new ArrayList<>();
        for (ProductCatalog productCatalog : productCatalogs) {
            ProductCatalogDetails productCatalogDetails;
            productCatalogDetails = new ProductCatalogDetails(productCatalog.getId(),
                    productCatalog.getProductCatalogName());
            detailsProductatalogList.add(productCatalogDetails);
        }
        return detailsProductatalogList;
    }
    
    private ProductCatalogDetails findById(Integer productCatalogId)
    {
        ProductCatalog productCatalog = em.find(ProductCatalog.class, productCatalogId);
        return new ProductCatalogDetails(productCatalog.getId(), productCatalog.getProductCatalogName());
    }

    public Collection<String> findProductCatalogNames(Collection<Integer> productCatalogIds) {
        LOG.info("findProductCatalogNames");
        List<String> productCatalogNames = (List<String>) em.createQuery("SELECT u.productCatalogName FROM ProductCatalog u WHERE u.id IN ?1")
                .setParameter(1, productCatalogIds)
                .getResultList();
        return productCatalogNames;
    }

}
