package com.pos.posproject.common;

/**
 *
 * @author Rori
 */
public class StatisticProductDetails {
    private Integer id;
    private String name;
    private Integer numberOfSales;

    public StatisticProductDetails(Integer id, String name, Integer numberOfSales) {
        this.id = id;
        this.name = name;
        this.numberOfSales = numberOfSales;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfSales() {
        return numberOfSales;
    }
     
}
