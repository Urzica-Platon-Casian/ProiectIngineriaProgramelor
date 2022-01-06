package com.pos.posproject.common;

import java.util.Date;

/**
 *
 * @author Rori
 */
public class PaymentDetails {
    private Integer id;
    private Double total;
    private Double amount;
    private Date date;
    private String paymentType;

    public PaymentDetails(Integer id, Double total, Double amount, Date date, String paymentType) {
        this.id = id;
        this.total = total;
        this.amount = amount;
        this.date = date;
        this.paymentType = paymentType;
    }

    public Integer getId() {
        return id;
    }

    public Double getTotal() {
        return total;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getPaymentType() {
        return paymentType;
    }  
}
