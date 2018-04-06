package com.sap.integration.constants;

import com.sap.integration.model.Customer;
import com.sap.integration.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ResourceUnionConstants {
	
	public static final String TYPE_CHAT = "chat";
	
	public static final String TYPE_COMMAND = "command";
	
	public static final String TYPE_TEXTINFO = "textInformation";

	public static final List<Product> DUMMY_PRODUCT_LIST = new ArrayList<>();
	
	public static final List<Customer> DUMMY_CUSTOMER_LIST = new ArrayList<>();

	static{
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 320",18));
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 450",12));
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 900",8));
		DUMMY_PRODUCT_LIST.add(new Product("SteelPlump_120",8));
		DUMMY_PRODUCT_LIST.add(new Product("InletPipe",13));
		DUMMY_PRODUCT_LIST.add(new Product("PLUMP 430",12));
		DUMMY_PRODUCT_LIST.add(new Product("Aqua ABC",4));
		DUMMY_PRODUCT_LIST.add(new Product("Coffee Machine filter",8));
		DUMMY_PRODUCT_LIST.add(new Product("QTP P30010112",18));
		DUMMY_PRODUCT_LIST.add(new Product("QTP P30010116",12));
		DUMMY_PRODUCT_LIST.add(new Product("MDECC-DS22",9));
		DUMMY_PRODUCT_LIST.add(new Product("CJ Mat P3001007-1120-1",8));
	}
	
	static{
		DUMMY_CUSTOMER_LIST.add(new Customer("Boris",38));
		DUMMY_CUSTOMER_LIST.add(new Customer("Aviva",32));
		DUMMY_CUSTOMER_LIST.add(new Customer("Xiaoming",48));
		DUMMY_CUSTOMER_LIST.add(new Customer("Rishav",8));
		DUMMY_CUSTOMER_LIST.add(new Customer("CMBC(TianXin Branch)",18));
		DUMMY_CUSTOMER_LIST.add(new Customer("7Eleven(Buiding Big)",12));
		DUMMY_CUSTOMER_LIST.add(new Customer("Nippon Transport(Chengdu)",41));
		DUMMY_CUSTOMER_LIST.add(new Customer("SF Express",8));
		DUMMY_CUSTOMER_LIST.add(new Customer("AutoNavi Map(Beijing)",33));
		DUMMY_CUSTOMER_LIST.add(new Customer("YongDa",16));
		DUMMY_CUSTOMER_LIST.add(new Customer("GreenMile",13));
	}

}
