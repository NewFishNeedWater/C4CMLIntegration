package com.sap.integration.utils.comparatpr;

import com.sap.integration.model.Customer;

import java.util.Comparator;

public class ComparatorCustomer implements Comparator<Customer> {

	@Override
	public int compare(Customer customer0, Customer customer1) {
		int flag = customer1.getNumOfQuoted().compareTo(
				customer0.getNumOfQuoted());
		return flag;
	}

}
