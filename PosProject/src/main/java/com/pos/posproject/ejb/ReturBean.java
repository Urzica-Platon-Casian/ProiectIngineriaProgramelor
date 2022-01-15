/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.ejb;

import com.pos.posproject.entity.LineItem;
import com.pos.posproject.entity.LineItemRetur;
import com.pos.posproject.entity.Product;
import com.pos.posproject.entity.Retur;
import com.pos.posproject.entity.Sale;
import com.pos.posproject.enums.ReturStatus;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Monica
 */
@Stateless
public class ReturBean {

    private static final Logger LOG = Logger.getLogger(SealeBean.class.getName());
    @PersistenceContext
    private EntityManager em;

    public Integer createRetur(int cashierID, int saleId) {
        Retur retur = new Retur();
        retur.setDate(new Date());
        retur.setStatus(ReturStatus.PENDING);
        retur.setCashierId(cashierID);
        retur.setSaleId(saleId);
        em.persist(retur);
        return retur.getId();
    }
    
    public Integer getSaleId(int returId){
        Retur retur = em.find(Retur.class, returId);
        return retur.getSaleId();
    }
    
    public void addReturItem(int itemId, int quantity, int returId){
        LineItem lineItem = em.find(LineItem.class, itemId);
        Retur retur = em.find(Retur.class, returId);
        LineItemRetur lineItemRetur = new LineItemRetur();
        lineItemRetur.setProduct(lineItem.getProduct());
        lineItemRetur.setQuantity(quantity);
        lineItemRetur.setRetur(retur);
        em.persist(lineItemRetur);
        Collection<LineItemRetur> items = retur.getReturProducts();
        items.add(lineItemRetur);
        retur.setReturProducts(items);
    }

    public void updateReturStatus(Integer returId) {
        LOG.info("updateSaleStatus");
        Retur retur = em.find(Retur.class, returId);
        retur.setStatus(ReturStatus.COMPLETED);
    }
    
    public Double getTotal(Integer returId) {
        Double total = 0.0;
        Retur retur = em.find(Retur.class, returId);
        Collection<LineItemRetur> lineItems = retur.getReturProducts();
        for (LineItemRetur lineItem : lineItems) {
            Product product = lineItem.getProduct();
            total = total + product.getPrice() * lineItem.getQuantity();
        }
        return total;
    }
}
