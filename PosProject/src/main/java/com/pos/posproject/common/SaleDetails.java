package com.pos.posproject.common;

import java.util.List;

/**
 *
 * @author upcas
 */
public class SaleDetails {
     private Integer id;
     private List<LineItemDetails> lineItems;

    public SaleDetails(Integer id, List<LineItemDetails> lineItems) {
        this.id = id;
        this.lineItems = lineItems;
    }

    public Integer getId() {
        return id;
    }

    public List<LineItemDetails> getLineItems() {
        return lineItems;
    }
}
