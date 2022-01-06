package com.pos.posproject.ejb;

import com.pos.posproject.common.LineItemDetails;
import com.pos.posproject.entity.LineItem;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author upcas
 */
@Stateful
@SessionScoped
public class SaleLineItemBean {    
    Set<LineItemDetails> saleLineItems = new HashSet<LineItemDetails>();

    public Set<LineItemDetails> getSaleLineItems() {
        return saleLineItems;
    }

    public void setSaleLineItems(Set<LineItemDetails> saleLineItems) {
        this.saleLineItems = saleLineItems;
    }
}
