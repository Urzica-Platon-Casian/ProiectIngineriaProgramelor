/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.ejb;

import com.pos.posproject.common.LineItemDetails;
import com.pos.posproject.common.ReturDetails;
import com.pos.posproject.entity.LineItem;
import com.pos.posproject.entity.Product;
import com.pos.posproject.entity.Retur;
import com.pos.posproject.enums.ReturStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Monica
 */
@Stateless
public class ReturBean {

    private static final Logger LOG = Logger.getLogger(SealeBean.class.getName());
    @PersistenceContext
    private EntityManager em;

    public Integer createRetur(int cashierID) {
        Retur retur = new Retur();
        retur.setDate(new Date());
        retur.setStatus(ReturStatus.PENDING);
        retur.setCashierId(cashierID);
        em.persist(retur);
        return retur.getId();
    }

    public ReturDetails findById(Integer returId) {
        Retur retur = em.find(Retur.class, returId);
        Collection<LineItem> lineItems = retur.getReturProducts();
        List<LineItemDetails> lineItemsDetails = new ArrayList<LineItemDetails>();
        for (LineItem lineItem : lineItems) {
            lineItemsDetails.add(new LineItemDetails(lineItem.getId(), lineItem.getQuantity(), lineItem.getProduct().getName(), lineItem.getProduct().getPrice()));
        }
        return new ReturDetails(retur.getId(), lineItemsDetails, retur.getCashierId(), retur.getDate());
    }

    public Double getTotal(Integer returId) {
        Double total = 0.0;
        Retur retur = em.find(Retur.class, returId);
        Collection<LineItem> lineItems = retur.getReturProducts();
        for (LineItem lineItem : lineItems) {
            Product product = lineItem.getProduct();
            total = total + product.getPrice() * lineItem.getQuantity();
        }
        return total;
    }

    public ReturDetails getReturDetails(Integer returId) {
        LOG.info("getAllProducts");
        try {
            List<LineItemDetails> lineItemsDetails = new ArrayList<>();
            TypedQuery<LineItem> typedQuery = em.createQuery("SELECT c FROM LineItem c where c.retur.id = :id", LineItem.class)
                    .setParameter("id", returId);
            Retur retur = em.find(Retur.class, returId);
            Integer cashierId = retur.getCashierId();
            List<LineItem> lineItems = typedQuery.getResultList();
            for (LineItem lineItem : lineItems) {
                LineItemDetails lineitemDetails = new LineItemDetails(lineItem.getId(), lineItem.getQuantity(),
                        lineItem.getProduct().getName(), lineItem.getProduct().getPrice());
                lineItemsDetails.add(lineitemDetails);
            }
            return new ReturDetails(returId, lineItemsDetails, cashierId, retur.getDate());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<LineItemDetails> getLineItem(Integer returId) {
        LOG.info("getAllProducts");
        try {
            List<LineItemDetails> lineItemsDetails = new ArrayList<>();
            TypedQuery<LineItem> typedQuery = em.createQuery("SELECT c FROM LineItem c where c.retur.id = :id", LineItem.class)
                    .setParameter("id", returId);
            List<LineItem> lineItems = typedQuery.getResultList();
            for (LineItem lineItem : lineItems) {
                LineItemDetails lineitemDetails = new LineItemDetails(lineItem.getId(), lineItem.getQuantity(),
                        lineItem.getProduct().getName(), lineItem.getProduct().getPrice());
                lineItemsDetails.add(lineitemDetails);
            }
            return lineItemsDetails;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
    public void updateStockOfProducts(Integer returId) {
        LOG.info("updateStockOfProducts");
         try {
            TypedQuery<LineItem> typedQuery = em.createQuery("SELECT c FROM LineItem c where c.retur.id = :id", LineItem.class)
                    .setParameter("id", returId);
            TypedQuery<Product> productsQuery = em.createQuery("SELECT e FROM Product e", Product.class);
            List<LineItem> lineItems = typedQuery.getResultList();
            List<Product> products = productsQuery.getResultList();
            for (LineItem lineItem : lineItems) {
                for(Product product : products)
                {
                    if(lineItem.getProduct().getId() == product.getId()) {
                        Integer quantity = product.getQuantity();
                        product.setQuantity(quantity+lineItem.getQuantity());
                    }
                }
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
    public void updateSaleStatus(Integer returId) {
        LOG.info("updateSaleStatus");
        Retur retur = em.find(Retur.class, returId);
        retur.setStatus(ReturStatus.COMPLETED);
    }
    
    public Boolean getStatus(Integer returId){
        LOG.info("getSaleStatus");
        Retur retur = em.find(Retur.class, returId);
        if(retur.getStatus() == ReturStatus.COMPLETED)
            return true;
        else
            return false;
    }
    
}
