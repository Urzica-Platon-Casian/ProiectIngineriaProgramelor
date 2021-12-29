/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.ejb;

import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Monica
 */
@Stateful
@SessionScoped
public class InvoiceBean {
     private Set<Integer> productCatalogIds=new HashSet<Integer>();
     
     public Set<Integer> getProductCatalogIds(){
         return productCatalogIds;
     }
     public void setProductCatalogIds(Set<Integer> productCatalogIds){
         this.productCatalogIds=productCatalogIds;
     }
    
}
