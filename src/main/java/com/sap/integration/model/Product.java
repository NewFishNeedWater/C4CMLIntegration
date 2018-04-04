package com.sap.integration.model;

public class Product {

    public Product(String material,Integer numOfQuoted){

        this.material = material;
        this.numOfQuoted = numOfQuoted;

    }

    private String material;
    private Integer numOfQuoted;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getNumOfQuoted() {
        return numOfQuoted;
    }

    public void setNumOfQuotated(Integer numOfQuoted) {
        this.numOfQuoted = numOfQuoted;
    }
}
