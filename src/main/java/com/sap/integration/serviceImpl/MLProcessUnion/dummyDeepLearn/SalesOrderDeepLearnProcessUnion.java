package com.sap.integration.serviceImpl.MLProcessUnion.dummyDeepLearn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sap.integration.model.ComparatorCustomer;
import com.sap.integration.model.Customer;
import com.sap.integration.utils.DumDeepLearnProcessUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.integration.constants.ResourceUnionConstants;
import com.sap.integration.constants.SapActionsConstants;
import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.responseVo.ResourceContent;
import com.sap.integration.vo.responseVo.ResourceUnion;
import com.sap.integration.vo.responseVo.ResourceUnionVo;

@Service
public class SalesOrderDeepLearnProcessUnion extends DumDeepLearnProcessUnion {

	@Autowired
	DumDeepLearnProcessUtility dumDeepLearnProcessUtility;

	/**
	 * Create Visit Without any customer info
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
		ResourceUnionVo texInfoUnion = generateTextInfoResourceForSalesOrderCustomerList(
				request, displayIndex++);
		result.add(texInfoUnion);
		return result;
	}

	/**
	 * Generate default TextInfo Resource Union for [Sales Order]
	 * 
	 * @param request
	 * @param successRate
	 * @param displayIndex
	 * @return
	 */
	private ResourceUnionVo generateTextInfoResourceForSalesOrderCustomerList(
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
						"text","",
						"Here are some customers might be suitable for create a sales order:");
		resourceContentList.add(content1);
		List<Customer> customerList = dumDeepLearnProcessUtility
				.getRandomCustomer(3);
		Collections.sort(customerList, new ComparatorCustomer());
		for (Customer customer : customerList) {
			ResourceContent content = dumDeepLearnProcessUtility
					.createResourceContent(
							dumDeepLearnProcessUtility
									.generateRandomHitRate(50),
							"text",
							"Customer Name:" + customer.getName()
									+ ", You have contacted",
									+ customer.getNumOfQuoted() + " times");
			resourceContentList.add(content);
		}

		return infoUnion;
	}

	@Override
	public ResourceUnionVo generateCreateCommandDescription(
			C4CUserActionVo request) {
		ResourceUnionVo resourceUnionVo = new ResourceUnionVo();
		ResourceUnion resourceUnion = new ResourceUnion();
		resourceUnion.setType(ResourceUnionConstants.TYPE_CHAT);
		// default index: 1, could be adjust later.
		resourceUnion.setDisplayIndex(1);
		String content = "Click this button to create a new Sales Order";
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
	 * Create Sales Order with customer information
	 * 
	 * @param request
	 * @return
	 */
	List<ResourceUnionVo> generateCreateResult(C4CUserActionVo request) {
		List<ResourceUnionVo> result = new ArrayList<ResourceUnionVo>();
		// 1: Generate [Create] Action Chat & description
		ResourceUnionVo homeCommandChatUnionVo = generateCreateCommandDescription(request);
		result.add(homeCommandChatUnionVo);
		// 2: Generate [Create] Action Button
		ResourceUnionVo homeCommandUnionVo = dumDeepLearnProcessUtility
				.copyToCommandResourceUnionDirectly(request);
		homeCommandUnionVo.getResourceUnion().setDisplayIndex(2);
		result.add(homeCommandUnionVo);
		
		

		return result;
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
