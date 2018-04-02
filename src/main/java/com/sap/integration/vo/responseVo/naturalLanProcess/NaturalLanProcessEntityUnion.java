package com.sap.integration.vo.responseVo.naturalLanProcess;

import java.util.ArrayList;


public class NaturalLanProcessEntityUnion {
	
	 private ArrayList<NaturalLanProcessEntity> command = new ArrayList<>();
	 
	 private ArrayList<NaturalLanProcessEntity> number = new ArrayList<>();
	 
	 private ArrayList<NaturalLanProcessEntity> ordertype = new ArrayList<>();
	 
	 private ArrayList<NaturalLanProcessEntity> customer = new ArrayList<>();

	public ArrayList<NaturalLanProcessEntity> getCommand() {
		return command;
	}

	public void setCommand(ArrayList<NaturalLanProcessEntity> command) {
		this.command = command;
	}

	public ArrayList<NaturalLanProcessEntity> getNumber() {
		return number;
	}

	public void setNumber(ArrayList<NaturalLanProcessEntity> number) {
		this.number = number;
	}

	public ArrayList<NaturalLanProcessEntity> getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(ArrayList<NaturalLanProcessEntity> ordertype) {
		this.ordertype = ordertype;
	}

	public ArrayList<NaturalLanProcessEntity> getCustomer() {
		return customer;
	}

	public void setCustomer(ArrayList<NaturalLanProcessEntity> customer) {
		this.customer = customer;
	}

}
