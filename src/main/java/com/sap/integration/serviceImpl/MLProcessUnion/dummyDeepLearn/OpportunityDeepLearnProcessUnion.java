package com.sap.integration.serviceImpl.MLProcessUnion.dummyDeepLearn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sap.integration.model.ComparatorCustomer;
import com.sap.integration.model.Customer;
import com.sap.integration.model.Product;
import com.sap.integration.utils.DumDeepLearnProcessUtility;
import com.sap.integration.vo.responseVo.ResourceContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.integration.constants.ResourceUnionConstants;
import com.sap.integration.constants.SapActionsConstants;
import com.sap.integration.constants.SapThingTypeConstants;
import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.responseVo.ResourceUnion;
import com.sap.integration.vo.responseVo.ResourceUnionVo;

@Service
public class OpportunityDeepLearnProcessUnion extends DumDeepLearnProcessUnion {

	@Autowired
	DumDeepLearnProcessUtility dumDeepLearnProcessUtility;

	@Override
	public ResourceUnionVo generateCreateCommandDescription(
			C4CUserActionVo request) {
		ResourceUnionVo resourceUnionVo = new ResourceUnionVo();
		ResourceUnion resourceUnion = new ResourceUnion();
		resourceUnion.setType(ResourceUnionConstants.TYPE_CHAT);
		// default index: 1, could be adjust later.
		resourceUnion.setDisplayIndex(1);
		String content = "Click this button to create a new Opportunity";
		String customerName = dumDeepLearnProcessUtility
				.getCustomerNameFromRequest(request);
		if (customerName != null) {
			content = content + " for customer:" + customerName;
		} else {
			content = content + ":";
		}
		resourceUnion.setContent(content);
		resourceUnionVo.setResourceUnion(resourceUnion);
		return resourceUnionVo;
	}

	/**
	 * Generate Create Opportunity Command Description
	 * 
	 * @param request
	 * @return
	 */
	private ResourceUnionVo generateCreateSalesOrderCommandDescription(
			C4CUserActionVo request, int successRate, boolean hasOtherOrders) {
		ResourceUnionVo resourceUnionVo = new ResourceUnionVo();
		ResourceUnion resourceUnion = new ResourceUnion();
		resourceUnion.setType(ResourceUnionConstants.TYPE_CHAT);
		// default index: 1, could be adjust later.
		resourceUnion.setDisplayIndex(3);
		String customerName = dumDeepLearnProcessUtility
				.getCustomerNameFromRequest(request);
		if (customerName == null) {
			// In case no customer found, just return null
			return null;
		}
		String content = null;
		if (hasOtherOrders) {
			content = "Or You can create an Sales Order for this customer, By Click the following button:";
		} else {
			content = "Success Rate for this customer is around: "
					+ successRate
					+ " , Since the success rate is pretty high, "
					+ " You can create an Sales Order for this customer directly, By Click the following button:";
		}
		resourceUnion.setContent(content);
		resourceUnion.setHitRate(dumDeepLearnProcessUtility
				.generateRandomHitRate(80));
		resourceUnionVo.setResourceUnion(resourceUnion);
		return resourceUnionVo;
	}

	/**
	 * Create Opportunity Without any customer info
	 * 
	 * @param request
	 * @return
	 */
	private List<ResourceUnionVo> generateCreateResultWithoutCustomer(
			C4CUserActionVo request) {
		List<ResourceUnionVo> result = new ArrayList<ResourceUnionVo>();
		// 1: Generate [Create] Action Chat & description
		int displayIndex = 1;
		ResourceUnionVo homeCommandChatUnionVo = generateCreateCommandDescription(request);
		result.add(homeCommandChatUnionVo);
		homeCommandChatUnionVo.getResourceUnion().setDisplayIndex(
				displayIndex++);
		// 2: Generate [Create] Action Button
		ResourceUnionVo homeCommandUnionVo = dumDeepLearnProcessUtility
				.copyToCommandResourceUnionDirectly(request);
		homeCommandUnionVo.getResourceUnion().setDisplayIndex(displayIndex++);
		homeCommandUnionVo.getResourceUnion().setHitRate(
				dumDeepLearnProcessUtility.generateRandomHitRate(80));
		result.add(homeCommandUnionVo);
		ResourceUnionVo texInfoUnion = generateTextInfoResourceForOppoCustomerList(
				request, displayIndex++);
		result.add(texInfoUnion);
		return result;
	}

	/**
	 * Generate default TextInfo Resource Union for suitable customer list
	 * 
	 * @param request
	 * @param successRate
	 * @param displayIndex
	 * @return
	 */
	private ResourceUnionVo generateTextInfoResourceForOppoCustomerList(
			C4CUserActionVo request, int displayIndex) {
		ResourceUnionVo infoUnion = new ResourceUnionVo();
		ResourceUnion resourceUnion = new ResourceUnion();
		infoUnion.setResourceUnion(resourceUnion);
		resourceUnion.setType(ResourceUnionConstants.TYPE_TEXTINFO);
		resourceUnion.setDisplayIndex(displayIndex++);
		List<ResourceContent> resourceContentList = new ArrayList<>();
		resourceUnion.setResourceContents(resourceContentList);

		ResourceContent content1 = dumDeepLearnProcessUtility
				.createResourceContent(
						dumDeepLearnProcessUtility.generateRandomHitRate(40),
						"text",
						"Here are some customers you have visited or created Lead recently, might be useful for your Opportunity creation:");
		resourceContentList.add(content1);
		List<Customer> customerList = dumDeepLearnProcessUtility
				.getRandomCustomer(3);
		Collections.sort(customerList, new ComparatorCustomer());
		for (Customer customer : customerList) {
			ResourceContent content = dumDeepLearnProcessUtility
					.createResourceContent(dumDeepLearnProcessUtility
							.generateRandomHitRate(50), "text",
							"Customer Name:" + customer.getName()
									+ ", You have created visits or leads:"
									+ customer.getNumOfQuoted()
									+ " times recently");
			resourceContentList.add(content);
		}

		return infoUnion;
	}

	

	/**
	 * Create Visit
	 * 
	 * @param request
	 * @return
	 */
	List<ResourceUnionVo> generateCreateResult(C4CUserActionVo request) {
		List<ResourceUnionVo> result = new ArrayList<ResourceUnionVo>();
		// 1: Generate [Create] Action Chat & description
		ResourceUnionVo homeCommandChatUnionVo = generateCreateCommandDescription(request);
		result.add(homeCommandChatUnionVo);
		homeCommandChatUnionVo.getResourceUnion().setHitRate(
				dumDeepLearnProcessUtility.generateRandomHitRate(80));
		int displayIndex = 1;
		homeCommandChatUnionVo.getResourceUnion().setDisplayIndex(
				displayIndex++);
		// 2: Generate [Create] Action Button
		ResourceUnionVo homeCommandUnionVo = dumDeepLearnProcessUtility
				.copyToCommandResourceUnionDirectly(request);
		homeCommandUnionVo.getResourceUnion().setDisplayIndex(displayIndex++);
		homeCommandUnionVo.getResourceUnion().setHitRate(
				dumDeepLearnProcessUtility.generateRandomHitRate(80));
		result.add(homeCommandUnionVo);
		// TODO dummy Logic to decide weather need to create relative lead or
		// oppor or sales order or donoting.
		int successRate = (int) (Math.random() * 100);
		if (successRate > 50) {
			// In case create a sales order in case success rate is good
			ResourceUnionVo salesOrderCommandChatUnionVo = generateCreateSalesOrderCommandDescription(
					request, successRate, true);
			result.add(salesOrderCommandChatUnionVo);
			salesOrderCommandChatUnionVo.getResourceUnion().setDisplayIndex(
					displayIndex++);
			ResourceUnionVo salesOrderUnionVo = dumDeepLearnProcessUtility
					.copyToCommandResourceUnionDirectly(request);
			salesOrderUnionVo.getResourceUnion().setAction(
					SapActionsConstants.CREATE);
			salesOrderUnionVo.getResourceUnion().setTarget(
					SapThingTypeConstants.SALES_ORDER);
			salesOrderUnionVo.getResourceUnion()
					.setDisplayIndex(displayIndex++);
			salesOrderUnionVo.getResourceUnion().setHitRate(
					dumDeepLearnProcessUtility.generateRandomHitRate(60));
			result.add(homeCommandUnionVo);
		}
		ResourceUnionVo infoUnion = generateDefaultTextInfoResourceForOpportunity(
				request, successRate, displayIndex++);
		result.add(infoUnion);

		return result;
	}

	/**
	 * Generate default TextInfo Resource Union for [Visit]
	 * 
	 * @param request
	 * @param successRate
	 * @param displayIndex
	 * @return
	 */
	private ResourceUnionVo generateDefaultTextInfoResourceForOpportunity(
			C4CUserActionVo request, int successRate, int displayIndex) {
		ResourceUnionVo infoUnion = new ResourceUnionVo();
		ResourceUnion resourceUnion = new ResourceUnion();
		infoUnion.setResourceUnion(resourceUnion);
		resourceUnion.setType(ResourceUnionConstants.TYPE_TEXTINFO);
		resourceUnion.setDisplayIndex(displayIndex++);
		List<ResourceContent> resourceContentList = new ArrayList<>();
		resourceUnion.setResourceContents(resourceContentList);
		String customerName = dumDeepLearnProcessUtility
				.getCustomerNameFromRequest(request);
		if (null != customerName) {
			ResourceContent content = dumDeepLearnProcessUtility
					.createResourceContent(dumDeepLearnProcessUtility
							.generateRandomHitRate(80), "title", customerName);
			resourceContentList.add(content);
		}

		ResourceContent content1 = dumDeepLearnProcessUtility
				.createResourceContent(
						dumDeepLearnProcessUtility.generateRandomHitRate(40),
						"text", "Sales Order Successful rate since 2018:"
								+ successRate + "%");
		resourceContentList.add(content1);

		ResourceContent content2 = dumDeepLearnProcessUtility
				.createResourceContent(
						dumDeepLearnProcessUtility.generateRandomHitRate(40),
						"title", "Mostly visited products");
		resourceContentList.add(content2);
		Product product2 = dumDeepLearnProcessUtility.getRandomProduct(1)
				.get(0);
		ResourceContent content3 = dumDeepLearnProcessUtility
				.createResourceContent(
						dumDeepLearnProcessUtility.generateRandomHitRate(50),
						"text",
						"Material:" + product2.getMaterial()
								+ ", Number of quoted:"
								+ product2.getNumOfQuoted());
		resourceContentList.add(content3);

		Product product = dumDeepLearnProcessUtility.getRandomProduct(1).get(0);
		ResourceContent content4 = dumDeepLearnProcessUtility
				.createResourceContent(
						dumDeepLearnProcessUtility.generateRandomHitRate(50),
						"text",
						"Material:" + product.getMaterial()
								+ ",  Number of quoted:"
								+ product.getNumOfQuoted());
		resourceContentList.add(content4);

		Product product1 = dumDeepLearnProcessUtility.getRandomProduct(1)
				.get(0);
		ResourceContent content5 = dumDeepLearnProcessUtility
				.createResourceContent(
						dumDeepLearnProcessUtility.generateRandomHitRate(50),
						"text",
						"Material:" + product1.getMaterial()
								+ ", Number of quoted:"
								+ product1.getNumOfQuoted());
		resourceContentList.add(content5);
		return infoUnion;
	}

	@Override
	public List<ResourceUnionVo> generateResult(C4CUserActionVo request) {
		String action = dumDeepLearnProcessUtility.getRequestAction(request);
		if (action != null && action.equals(SapActionsConstants.CREATE)) {
			// In case: Create Sales Order
			String customerName = dumDeepLearnProcessUtility
					.getCustomerNameFromRequest(request);
			if (customerName == null) {
				// In case no customer found, just return null
				return generateCreateResultWithoutCustomer(request);
			}
			return generateCreateResult(request);
		}
		return null;
	}

}
