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
public class ProductDetails implements java.io.Serializable{
    
    private Integer id;
    private String barcode;
    private String name;
    private String description;
    private Double price;
    private String category;
    private String productCatalogName;

    public Integer getId() {
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getProductCatalogName() {
        return productCatalogName;
    }

    public ProductDetails(Integer id, String barcode, String name, String description, Double price, String category, String productCatalogName) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.productCatalogName = productCatalogName;
    }

   
    
    
    
}
