package com.sap.integration.model;

public class Customer {

    public Customer(String name,Integer numOfQuoted){

        this.name = name;
        this.numOfQuoted = numOfQuoted;

    }

    private String name;
    private Integer numOfQuoted;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumOfQuoted(Integer numOfQuoted) {
		this.numOfQuoted = numOfQuoted;
	}

	public Integer getNumOfQuoted() {
        return numOfQuoted;
    }

    public void setNumOfQuotated(Integer numOfQuoted) {
        this.numOfQuoted = numOfQuoted;
    }
}
