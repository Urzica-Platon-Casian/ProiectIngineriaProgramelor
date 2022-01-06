package com.pos.posproject.ejb;

import com.pos.posproject.common.LineItemDetails;
import com.pos.posproject.common.SaleDetails;
import com.pos.posproject.entity.LineItem;
import com.pos.posproject.entity.Product;
import com.pos.posproject.entity.Sale;
import com.pos.posproject.enums.SaleStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author upcas
 */
@Stateless
public class SealeBean {

    private static final Logger LOG = Logger.getLogger(SealeBean.class.getName());
    @PersistenceContext
    private EntityManager em;

    public Integer createSale(int cashierID) {
        Sale sale = new Sale();
        sale.setDate(new Date());
        sale.setStatus(SaleStatus.PENDING);
        sale.setCashierId(cashierID);
        em.persist(sale);
        return sale.getId();
    }

    public SaleDetails findById(Integer saleId) {
        Sale sale = em.find(Sale.class, saleId);
        Collection<LineItem> lineItems = sale.getSaleProducts();
        List<LineItemDetails> lineItemsDetails = new ArrayList<LineItemDetails>();
        for (LineItem lineItem : lineItems) {
            lineItemsDetails.add(new LineItemDetails(lineItem.getId(), lineItem.getQuantity(), lineItem.getProduct().getName(), lineItem.getProduct().getPrice()));
        }
        return new SaleDetails(sale.getId(), lineItemsDetails, sale.getCashierId(), sale.getDate());
    }

    public Double getTotal(Integer saleId) {
        Double total = 0.0;
        Sale sale = em.find(Sale.class, saleId);
        Collection<LineItem> lineItems = sale.getSaleProducts();
        for (LineItem lineItem : lineItems) {
            Product product = lineItem.getProduct();
            total = total + product.getPrice() * lineItem.getQuantity();
        }
        return total;
    }

    public void updateSaleStatus(Integer saleId) {
        LOG.info("updateSaleStatus");
        Sale sale = em.find(Sale.class, saleId);
        sale.setStatus(SaleStatus.COMPLETED);
    }

    public SaleDetails getSaleDetails(Integer saleId) {
        LOG.info("getAllProducts");
        try {
            List<LineItemDetails> lineItemsDetails = new ArrayList<>();
            TypedQuery<LineItem> typedQuery = em.createQuery("SELECT c FROM LineItem c where c.sale.id = :id", LineItem.class)
                    .setParameter("id", saleId);
            Sale sale = em.find(Sale.class, saleId);
            Integer cashierId = sale.getCashierId();
            List<LineItem> lineItems = typedQuery.getResultList();
            for (LineItem lineItem : lineItems) {
                LineItemDetails lineitemDetails = new LineItemDetails(lineItem.getId(), lineItem.getQuantity(),
                        lineItem.getProduct().getName(), lineItem.getProduct().getPrice());
                lineItemsDetails.add(lineitemDetails);
            }
            return new SaleDetails(saleId, lineItemsDetails, cashierId, sale.getDate());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<LineItemDetails> getLineItem(Integer saleId) {
        LOG.info("getAllProducts");
        try {
            List<LineItemDetails> lineItemsDetails = new ArrayList<>();
            TypedQuery<LineItem> typedQuery = em.createQuery("SELECT c FROM LineItem c where c.sale.id = :id", LineItem.class)
                    .setParameter("id", saleId);
            //Sale sale = em.find(Sale.class, saleId);
            //Integer cashierId = sale.getCashierId();
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
    
    public void updateSaleStatus(Integer saleId) {
        LOG.info("updateSaleStatus");
        Sale sale = em.find(Sale.class, saleId);
        sale.setStatus(SaleStatus.COMPLETED);
    }
    
    public Boolean getStatus(Integer saleId){
        LOG.info("getSaleStatus");
        Sale sale = em.find(Sale.class, saleId);
        if(sale.getStatus() == SaleStatus.COMPLETED)
            return true;
        else
            return false;
    }
    
}
