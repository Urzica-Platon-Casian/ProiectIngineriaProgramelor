/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.entity;

import com.pos.posproject.enums.ReturMethods;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Monica
 */
public class Moneyback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;   
    private Double amount;
    private Double total;
    private Date date;
    
    @Enumerated(EnumType.ORDINAL)
    private ReturMethods method;
    
    @OneToOne
    @JoinColumn(name = "RETUR_KEY")
    Retur retur;
    
    @OneToOne(mappedBy = "retur", cascade = CascadeType.ALL)
    PayByCash payByCash;
    
    @OneToOne(mappedBy = "retur", cascade = CascadeType.ALL)
    PayByCard payByCard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReturMethods getMethod() {
        return method;
    }

    public void setMethod(ReturMethods method) {
        this.method = method;
    }

    public Retur getRetur() {
        return retur;
    }

    public void setRetur(Retur retur) {
        this.retur = retur;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


 

    public PayByCash getPayByCash() {
        return payByCash;
    }

    public void setPayByCash(PayByCash payByCash) {
        this.payByCash = payByCash;
    }

    public PayByCard getPayByCard() {
        return payByCard;
    }

    public void setPayByCard(PayByCard payByCard) {
        this.payByCard = payByCard;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retur)) {
            return false;
        }
        Retur other = (Retur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pos.posproject.entity.Moneyback[ id=" + id + " ]";
    }
    
}

