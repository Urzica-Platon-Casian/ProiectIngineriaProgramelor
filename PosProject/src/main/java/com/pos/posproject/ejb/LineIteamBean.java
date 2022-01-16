package com.pos.posproject.ejb;

import com.pos.posproject.common.LineItemDetails;
import com.pos.posproject.common.ProductDetails;
import com.pos.posproject.entity.LineItem;
import com.pos.posproject.entity.Product;
import com.pos.posproject.entity.ProductCatalog;
import com.pos.posproject.entity.Sale;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author upcas
 */
@Stateless
public class LineIteamBean {

    private static final Logger LOG = Logger.getLogger(LineIteamBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    public void createLineItem(int quantity, int productId, int saleId) {
        LOG.info("createLineItem");
        LineItem lineItem = new LineItem();
        lineItem.setQuantity(quantity);

        Sale sale = em.find(Sale.class, saleId);
        Product product = em.find(Product.class, productId);

        lineItem.setProduct(product);
        lineItem.setSale(sale);

        Collection<LineItem> lineItems = sale.getSaleProducts();
        lineItems.add(lineItem);
        sale.setSaleProducts(lineItems);

        em.persist(lineItem);
    }

    public List<LineItemDetails> copyLineItemsToDetails(List<LineItem> lineItems) {
        List<LineItemDetails> lineItemsDetails = new ArrayList<>();
        for (LineItem lineItem : lineItems) {
            LineItemDetails lineitemDetails = new LineItemDetails(lineItem.getId(), lineItem.getQuantity(),
                    lineItem.getProduct().getName(), lineItem.getProduct().getPrice());
            lineItemsDetails.add(lineitemDetails);
        }
        return lineItemsDetails;
    }
     
    public LineItemDetails findById(Integer lineItemId)
    {
        LineItem lineItem = em.find(LineItem.class, lineItemId);
       return new LineItemDetails(lineItem.getId(), lineItem.getQuantity(), lineItem.getProduct().getName(), lineItem.getProduct().getPrice());
        
    }
    
     public void deleteLineItemById(Integer lineItemId) {
        LineItem lineItem = em.find(LineItem.class, lineItemId);
        Sale sale = lineItem.getSale();
        sale.getSaleProducts().remove(lineItem);
        em.remove(lineItem);
    }
}
