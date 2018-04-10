package com.sap.integration.serviceImpl.MLProcessUnion.dummyDeepLearn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sap.integration.model.CommandPara;

import com.sap.integration.model.Customer;
import com.sap.integration.model.Product;
import com.sap.integration.model.VisitTimeModel;
import com.sap.integration.utils.DumDeepLearnProcessUtility;
import com.sap.integration.utils.comparatpr.ComparatorCustomer;
import com.sap.integration.vo.responseVo.ResourceContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.integration.constants.ResourceUnionConstants;
import com.sap.integration.constants.SapActionsConstants;
import com.sap.integration.constants.SapParameterConstants;
import com.sap.integration.constants.SapThingTypeConstants;
import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.responseVo.ResourceUnion;
import com.sap.integration.vo.responseVo.ResourceUnionVo;

/**
 * Process Union For ThingType "Visit"
 * 
 * @author I043125
 *
 */
@Service
public class VisitDeepLearnProcessUnion extends DumDeepLearnProcessUnion {

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
		String content = "Click this button to create a new visit";
		String customerName = dumDeepLearnProcessUtility
				.getCustomerNameFromRequest(request);
		if (customerName != null) {
			content = content + " for customer:" + customerName;
		} else {
			content = content + ":";
		}
		resourceUnion.setContent(content);
		resourceUnion.setHitRate(dumDeepLearnProcessUtility
				.generateRandomHitRate(80));
		resourceUnionVo.setResourceUnion(resourceUnion);
		return resourceUnionVo;
	}
	
	public ResourceUnionVo generateVisitTimeRecommendDescription(
			C4CUserActionVo request, VisitTimeModel visitTimeModel) {
		String customerName = dumDeepLearnProcessUtility
				.getCustomerNameFromRequest(request);
		if(customerName == null){
			return null;
		}
		ResourceUnionVo resourceUnionVo = new ResourceUnionVo();
		ResourceUnion resourceUnion = new ResourceUnion();
		resourceUnion.setType(ResourceUnionConstants.TYPE_CHAT);
		// default index: 1, could be adjust later.
		resourceUnion.setDisplayIndex(1);
		String content = "Usually visit time for this customer is:";
		
		content = content + visitTimeModel.getStartTime() + " - " + visitTimeModel.getEndTime();
		resourceUnion.setContent(content);
		resourceUnion.setHitRate(dumDeepLearnProcessUtility
				.generateRandomHitRate(80));
		resourceUnionVo.setResourceUnion(resourceUnion);
		return resourceUnionVo;
	}

	/**
	 * Generate Create Lead Command Description
	 * 
	 * @param request
	 * @return
	 */
	private ResourceUnionVo generateCreateLeadCommandDescription(
			C4CUserActionVo request) {
		ResourceUnionVo resourceUnionVo = new ResourceUnionVo();
		ResourceUnion resourceUnion = new ResourceUnion();
		resourceUnion.setType(ResourceUnionConstants.TYPE_CHAT);
		// default index: 1, could be adjust later.
		resourceUnion.setDisplayIndex(3);
		String content = "IF neccessary, Click this button to create a new Lead ";
		String customerName = dumDeepLearnProcessUtility
				.getCustomerNameFromRequest(request);
		if (customerName != null) {
			content = content + " for customer:" + customerName;
		} else {
			content = content + ":";
		}
		resourceUnion.setContent(content);
		resourceUnion.setHitRate(dumDeepLearnProcessUtility
				.generateRandomHitRate(80));
		resourceUnionVo.setResourceUnion(resourceUnion);
		return resourceUnionVo;
	}

	/**
	 * Generate Create Opportunity Command Description
	 * 
	 * @param request
	 * @return
	 */
	private ResourceUnionVo generateCreateOpportunityCommandDescription(
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
			content = "Or You can create an Opportunity for this customer, By Click the following button:";
		} else {
			content = "Success Rate for this customer is around: "
					+ successRate
					+ " You can create an Opportunity for this customer by Click the following button:";
		}
		resourceUnion.setContent(content);
		resourceUnion.setHitRate(dumDeepLearnProcessUtility
				.generateRandomHitRate(80));
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
	 * Generate default TextInfo Resource Union for [Visit]
	 * 
	 * @param request
	 * @param displayIndex
	 * @return
	 */
	private ResourceUnionVo generateTextInfoResourceForVisitCustomerList(
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
						"text", "", "Here are some customers might be interested for your visit:");
		resourceContentList.add(content1);
		List<Customer> customerList = dumDeepLearnProcessUtility.getRandomCustomer(3);
		Collections.sort(customerList, new ComparatorCustomer());
		for(Customer customer:customerList){
			ResourceContent content = dumDeepLearnProcessUtility
					.createResourceContent(
							dumDeepLearnProcessUtility.generateRandomHitRate(50),
							"text",
							"Customer Name:" + customer.getName()
									+ ", You have searched",
									 customer.getNumOfQuoted() + " times") ;
			resourceContentList.add(content);
		}
		
		
		return infoUnion;
	}


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
		homeCommandChatUnionVo.getResourceUnion().setDisplayIndex(displayIndex++);
		// 2: Generate [Create] Action Button
		ResourceUnionVo homeCommandUnionVo = dumDeepLearnProcessUtility
				.copyToCommandResourceUnionDirectly(request);
		homeCommandUnionVo.getResourceUnion().setDisplayIndex(displayIndex++);
		homeCommandUnionVo.getResourceUnion().setHitRate(
				dumDeepLearnProcessUtility.generateRandomHitRate(80));
		result.add(homeCommandUnionVo);
		ResourceUnionVo texInfoUnion = generateTextInfoResourceForVisitCustomerList(request, displayIndex++);
		result.add(texInfoUnion);
		return result;
	}
	
	private void _addStartTimeEndTimeToCommandPara(ResourceUnionVo resourceUnionVo, VisitTimeModel visitTimeModel){
		
		List<CommandPara> commandParas = resourceUnionVo.getResourceUnion().getCommandParas();
		CommandPara startTimePara = new CommandPara();
		startTimePara.setParaValue(SapParameterConstants.STARTTIME);
		startTimePara.setParaName(visitTimeModel.getStartTime());
		commandParas.add(startTimePara);
		CommandPara endTimePara = new CommandPara();
		endTimePara.setParaValue(SapParameterConstants.ENDTIME);
		endTimePara.setParaName(visitTimeModel.getEndTime());
		commandParas.add(endTimePara);
		resourceUnionVo.getResourceUnion().setCommandParas(commandParas);
	}

	/**
	 * Create Visit with specified customer
	 * 
	 * @param request
	 * @return
	 */
	private List<ResourceUnionVo> generateCreateResult(C4CUserActionVo request) {
		List<ResourceUnionVo> result = new ArrayList<ResourceUnionVo>();
		// 1: Generate [Create] Action Chat & description
		ResourceUnionVo homeCommandChatUnionVo = generateCreateCommandDescription(request);
		result.add(homeCommandChatUnionVo);
		// 1.5: Generate the default visit time
		String customerName = dumDeepLearnProcessUtility
				.getCustomerNameFromRequest(request);
		VisitTimeModel visitTimeModel = dumDeepLearnProcessUtility.getPreVisitTimeModelByUser(customerName);
		if(visitTimeModel == null){
			visitTimeModel = dumDeepLearnProcessUtility.getRandomVisitTimeModel();
		}
		
		
		// 2: Generate [Create] Action Button
		ResourceUnionVo homeCommandUnionVo = dumDeepLearnProcessUtility.copyToCommandResourceUnionDirectly(request);
		homeCommandUnionVo.getResourceUnion().setDisplayIndex(2);
		homeCommandUnionVo.getResourceUnion().setHitRate(
				dumDeepLearnProcessUtility.generateRandomHitRate(80));
		ResourceUnionVo visitTimeUnionVo = generateVisitTimeRecommendDescription(request, visitTimeModel);
		result.add(visitTimeUnionVo);
		_addStartTimeEndTimeToCommandPara(homeCommandUnionVo, visitTimeModel);
		result.add(homeCommandUnionVo);
		int successRate = (int) (Math.random() * 100);
		int displayIndex = 3;
		if (successRate <= 50) {
			// In case create a lead
			ResourceUnionVo leadCommandChatUnionVo = generateCreateLeadCommandDescription(request);
			result.add(leadCommandChatUnionVo);
			ResourceUnionVo leadUnionVo = dumDeepLearnProcessUtility
					.copyToCommandResourceUnionDirectly(request);
			leadUnionVo.getResourceUnion()
					.setAction(SapActionsConstants.CREATE);
			leadUnionVo.getResourceUnion()
					.setTarget(SapThingTypeConstants.LEAD);
			leadUnionVo.getResourceUnion().setDisplayIndex(displayIndex++);
			leadUnionVo.getResourceUnion().setHitRate(
					dumDeepLearnProcessUtility.generateRandomHitRate(60));
			result.add(leadUnionVo);
			if (successRate > 35) {
				// Also Give customer a choice to create an opportunity:
				ResourceUnionVo oppCommandChatUnionVo = generateCreateOpportunityCommandDescription(
						request, successRate, true);
				oppCommandChatUnionVo.getResourceUnion().setDisplayIndex(
						displayIndex++);
				result.add(oppCommandChatUnionVo);
				ResourceUnionVo opportunityUnionVo = dumDeepLearnProcessUtility
						.copyToCommandResourceUnionDirectly(request);
				opportunityUnionVo.getResourceUnion().setAction(
						SapActionsConstants.CREATE);
				opportunityUnionVo.getResourceUnion().setTarget(
						SapThingTypeConstants.OPPORTUNITY);
				opportunityUnionVo.getResourceUnion().setHitRate(
						dumDeepLearnProcessUtility.generateRandomHitRate(60));
				opportunityUnionVo.getResourceUnion().setDisplayIndex(
						displayIndex++);
				result.add(opportunityUnionVo);
			}
		}
		if (successRate > 50 && successRate <= 80) {
			// In case create a opportunity
			ResourceUnionVo oppCommandChatUnionVo = generateCreateOpportunityCommandDescription(
					request, successRate, false);
			oppCommandChatUnionVo.getResourceUnion().setDisplayIndex(
					displayIndex++);

			result.add(oppCommandChatUnionVo);
			ResourceUnionVo oppUnionVo = dumDeepLearnProcessUtility
					.copyToCommandResourceUnionDirectly(request);
			oppUnionVo.getResourceUnion().setAction(SapActionsConstants.CREATE);
			oppUnionVo.getResourceUnion().setHitRate(
					dumDeepLearnProcessUtility.generateRandomHitRate(60));
			oppUnionVo.getResourceUnion().setTarget(
					SapThingTypeConstants.OPPORTUNITY);
			oppUnionVo.getResourceUnion().setDisplayIndex(displayIndex++);
			result.add(oppUnionVo);
			if (successRate > 65) {
				// Also Give customer a choice to create sales order:
				ResourceUnionVo salesOrderCommandChatUnionVo = generateCreateSalesOrderCommandDescription(
						request, successRate, true);
				salesOrderCommandChatUnionVo.getResourceUnion()
						.setDisplayIndex(displayIndex++);
				result.add(salesOrderCommandChatUnionVo);
				ResourceUnionVo salesOrderUnionVo = dumDeepLearnProcessUtility
						.copyToCommandResourceUnionDirectly(request);
				salesOrderUnionVo.getResourceUnion().setHitRate(
						dumDeepLearnProcessUtility.generateRandomHitRate(40));
				salesOrderUnionVo.getResourceUnion().setAction(
						SapActionsConstants.CREATE);
				salesOrderUnionVo.getResourceUnion().setTarget(
						SapThingTypeConstants.SALES_ORDER);
				salesOrderUnionVo.getResourceUnion().setDisplayIndex(
						displayIndex++);
				result.add(salesOrderUnionVo);
			}
		}
		if (successRate > 80) {
			// In case create a sales order if success rate is bigger than 80%
			ResourceUnionVo salesOrderCommandChatUnionVo = generateCreateSalesOrderCommandDescription(
					request, successRate, false);
			result.add(salesOrderCommandChatUnionVo);
			ResourceUnionVo salesOrderUnionVo = dumDeepLearnProcessUtility
					.copyToCommandResourceUnionDirectly(request);
			salesOrderUnionVo.getResourceUnion().setAction(
					SapActionsConstants.CREATE);
			salesOrderUnionVo.getResourceUnion().setHitRate(
					dumDeepLearnProcessUtility.generateRandomHitRate(60));
			salesOrderUnionVo.getResourceUnion().setTarget(
					SapThingTypeConstants.SALES_ORDER);
			salesOrderUnionVo.getResourceUnion()
					.setDisplayIndex(displayIndex++);
			result.add(salesOrderUnionVo);
		}
		ResourceUnionVo textInfoUnion = this
				.generateDefaultTextInfoResourceForVisit(request, successRate,
						displayIndex);
		result.add(textInfoUnion);

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
	private ResourceUnionVo generateDefaultTextInfoResourceForVisit(
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
							.generateRandomHitRate(80), "title", "Customer", customerName);
			resourceContentList.add(content);
		}

		ResourceContent content1 = dumDeepLearnProcessUtility
				.createResourceContent(
						dumDeepLearnProcessUtility.generateRandomHitRate(40),
						"text", "Sales Order Successful rate since 2018", 
								successRate + "%");
		resourceContentList.add(content1);

		ResourceContent content2 = dumDeepLearnProcessUtility
				.createResourceContent(
						dumDeepLearnProcessUtility.generateRandomHitRate(40),
						"title", "", "Mostly visited products");
		resourceContentList.add(content2);
		Product product2 = dumDeepLearnProcessUtility.getRandomProduct(1)
				.get(0);
		ResourceContent content3 = dumDeepLearnProcessUtility
				.createResourceContent(
						dumDeepLearnProcessUtility.generateRandomHitRate(50),
						"text",
						"Material:" + product2.getMaterial()
								+ " Number of quoted",
								"" + product2.getNumOfQuoted());
		resourceContentList.add(content3);

		Product product = dumDeepLearnProcessUtility.getRandomProduct(1).get(0);
		ResourceContent content4 = dumDeepLearnProcessUtility
				.createResourceContent(
						dumDeepLearnProcessUtility.generateRandomHitRate(50),
						"text",
						"Material:" + product.getMaterial()
								+ " Number of quoted",
								"" + product.getNumOfQuoted());
		resourceContentList.add(content4);

		Product product1 = dumDeepLearnProcessUtility.getRandomProduct(1)
				.get(0);
		ResourceContent content5 = dumDeepLearnProcessUtility
				.createResourceContent(
						dumDeepLearnProcessUtility.generateRandomHitRate(50),
						"text",
						"Material:" + product1.getMaterial()
								+ " Number of quoted",
								""+ product1.getNumOfQuoted());
		resourceContentList.add(content5);
		return infoUnion;
	}

	@Override
	public List<ResourceUnionVo> generateResult(C4CUserActionVo request) {

		String action = dumDeepLearnProcessUtility.getRequestAction(request);
		if (action != null && action.equals(SapActionsConstants.CREATE)) {
			// In case: Create Visit
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
