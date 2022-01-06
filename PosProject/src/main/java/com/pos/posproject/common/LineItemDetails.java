package com.pos.posproject.common;

/**
 *
 * @author upcas
 */
public class LineItemDetails {
    private Integer id;
    private int quantity;
    private String productName;
    private Double price;

    public LineItemDetails(Integer id, int quantity, String productName, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
    }
    
    public Integer getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    } 
}
