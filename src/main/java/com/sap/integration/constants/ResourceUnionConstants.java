package com.sap.integration.constants;

import com.sap.integration.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ResourceUnionConstants {
	
	public static final String TYPE_CHAT = "chat";
	
	public static final String TYPE_COMMAND = "command";
	
	public static final String TYPE_TEXTINFO = "textInformation";

	public static final List<Product> DUMMY_PRODUCT_LIST = new ArrayList<>();

	static{
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 320",18));
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 450",12));
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 900",68));
		DUMMY_PRODUCT_LIST.add(new Product("HARRY 120",8));
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 330",18));
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 430",12));
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 910",68));
		DUMMY_PRODUCT_LIST.add(new Product("HARRY 150",8));
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 350",18));
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 476",12));
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 977",68));
		DUMMY_PRODUCT_LIST.add(new Product("HARRY 188",8));
	}

}
