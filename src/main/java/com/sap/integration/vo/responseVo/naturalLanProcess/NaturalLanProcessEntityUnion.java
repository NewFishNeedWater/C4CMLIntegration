package com.sap.integration.vo.responseVo.naturalLanProcess;

import java.util.ArrayList;
import java.util.List;


public class NaturalLanProcessEntityUnion {
	
	private List<NaturalLanProcessEntity> pronoun = new ArrayList<>();

    private List<NaturalLanProcessEntity> command = new ArrayList<>();

    private List<NaturalLanProcessEntity> number = new ArrayList<>();

    private List<NaturalLanProcessEntity> person = new ArrayList<>();

    private List<NaturalLanProcessEntity> ordertype = new ArrayList<>();

    private List<NaturalLanProcessEntity> customer = new ArrayList<>();
    
    private List<NaturalLanProcessEntity> customerprefix = new ArrayList<>();

    public List<NaturalLanProcessEntity> getPronoun() {
		return pronoun;
	}

	public void setPronoun(List<NaturalLanProcessEntity> pronoun) {
		this.pronoun = pronoun;
	}

	public List<NaturalLanProcessEntity> getCustomerprefix() {
        return customerprefix;
    }

    public void setCustomerprefix(List<NaturalLanProcessEntity> customerprefix) {
        this.customerprefix = customerprefix;
    }

    public List<NaturalLanProcessEntity> getPerson() {
        return person;
    }

    public void setPerson(List<NaturalLanProcessEntity> person) {
        this.person = person;
    }

    public List<NaturalLanProcessEntity> getCommand() {
        return command;
    }

    public void setCommand(List<NaturalLanProcessEntity> command) {
        this.command = command;
    }

    public List<NaturalLanProcessEntity> getNumber() {
        return number;
    }

    public void setNumber(List<NaturalLanProcessEntity> number) {
        this.number = number;
    }

    public List<NaturalLanProcessEntity> getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(List<NaturalLanProcessEntity> ordertype) {
        this.ordertype = ordertype;
    }

    public List<NaturalLanProcessEntity> getCustomer() {
        return customer;
    }

    public void setCustomer(List<NaturalLanProcessEntity> customer) {
        this.customer = customer;
    }
}
