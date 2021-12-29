/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.common;

/**
 *
 * @author Monica
 */
public class ProductCatalogDetails implements java.io.Serializable {
     private Integer id;
    private String productCatalogName;

    public Integer getId() {
        return id;
    }

    public String getProductCatalogName() {
        return productCatalogName;
    }

    public ProductCatalogDetails(Integer id, String productCatalogName) {
        this.id = id;
        this.productCatalogName = productCatalogName;
    }


 
    
}
