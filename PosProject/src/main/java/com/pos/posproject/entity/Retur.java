/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.entity;

import com.pos.posproject.enums.ReturStatus;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Monica
 */
@Entity
public class Retur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Integer id;

    private int cashierId;
    private Date date;
    private int saleId;

    @Enumerated(EnumType.ORDINAL)
    private ReturStatus status;

    @JsonbTransient
    @OneToMany(mappedBy = "retur")
    private Collection<LineItemRetur> returProducts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public ReturStatus getStatus() {
        return status;
    }
    
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Collection<LineItemRetur> getReturProducts() {
        return returProducts;
    }

    public void setReturProducts(Collection<LineItemRetur> returProducts) {
        this.returProducts = returProducts;
    }
    
    public void setStatus(ReturStatus status) {
        this.status = status;
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
        return "com.pos.posproject.entity.Sale[ id=" + id + " ]";
    }

}
