package com.pos.posproject.common;

import java.util.Date;
import java.util.List;

/**
 *
 * @author upcas
 */
public class SaleDetails {
     private Integer id;
     private List<LineItemDetails> lineItems;
     private Integer cashierId;
     private Date date;

    public SaleDetails(Integer id, List<LineItemDetails> lineItems, Integer cashierId, Date date) {
        this.id = id;
        this.lineItems = lineItems;
        this.cashierId = cashierId;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public List<LineItemDetails> getLineItems() {
        return lineItems;
    }

    public Integer getCashierId() {
        return cashierId;
    }

    public Date getDate() {
        return date;
    }
    
    
}
