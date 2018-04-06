package com.sap.integration.model;

import java.util.Comparator;

public class ComparatorProduct implements Comparator<Product> {

	@Override
	public int compare(Product product0, Product product1) {
		int flag = product1.getNumOfQuoted().compareTo(
				product0.getNumOfQuoted());
		return flag;
	}

}
