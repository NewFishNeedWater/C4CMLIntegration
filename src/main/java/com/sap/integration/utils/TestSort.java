package com.sap.integration.utils;

import java.util.Collections;
import java.util.List;

import com.sap.integration.constants.ResourceUnionConstants;
import com.sap.integration.utils.comparatpr.ComparatorCustomer;
import com.sap.integration.model.Customer;

public class TestSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Customer> customerList = ResourceUnionConstants.DUMMY_CUSTOMER_LIST;
		Collections.sort(customerList, new ComparatorCustomer());
		for(int i = 0; i < customerList.size(); i++){
			Customer customer = customerList.get(i);
			System.out.println(customer.getName() + "--" + customer.getNumOfQuoted());
		}

	}

}
