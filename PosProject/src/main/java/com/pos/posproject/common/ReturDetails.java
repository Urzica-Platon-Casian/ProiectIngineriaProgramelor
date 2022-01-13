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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<LineItemDetails> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemDetails> lineItems) {
        this.lineItems = lineItems;
    }

    public Integer getCashierId() {
        return cashierId;
    }

    public void setCashierId(Integer cashierId) {
        this.cashierId = cashierId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public ReturDetails(Integer id, List<LineItemDetails> lineItems, Integer cashierId, Date date) {
        this.id = id;
        this.lineItems = lineItems;
        this.cashierId = cashierId;
        this.date = date;
    }
     
     
}
