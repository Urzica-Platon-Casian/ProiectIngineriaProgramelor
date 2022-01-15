/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.common;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Monica
 */
public class ReturDetails {
     private Integer id;
     private List<LineItemDetails> lineItems;
     private Integer cashierId;
     private Date date;
     private Integer saleId;

    public Integer getSaleId() {
        return saleId;
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

    public void setDate(Date date) {
        this.date = date;
    }

    public ReturDetails(Integer id, List<LineItemDetails> lineItems, Integer cashierId, Date date, Integer saleId) {
        this.id = id;
        this.lineItems = lineItems;
        this.cashierId = cashierId;
        this.date = date;
        this.saleId = saleId;
    }

     
}
