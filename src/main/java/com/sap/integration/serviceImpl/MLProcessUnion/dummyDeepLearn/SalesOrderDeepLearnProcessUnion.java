package com.sap.integration.serviceImpl.MLProcessUnion.dummyDeepLearn;

import java.util.ArrayList;
import java.util.List;

import com.sap.integration.utils.DumDeepLearnProcessUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.integration.constants.ResourceUnionConstants;
import com.sap.integration.constants.SapActionsConstants;
import com.sap.integration.constants.SapThingTypeConstants;
import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.responseVo.ResourceUnion;
import com.sap.integration.vo.responseVo.ResourceUnionVo;

@Service
public class SalesOrderDeepLearnProcessUnion extends DumDeepLearnProcessUnion{
	
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
		String content = "Click this button to create a new Sales Order";
		String customerName = dumDeepLearnProcessUtility.getCustomerNameFromRequest(request);
		if(customerName != null){
			content = content + " for customer:" + customerName;
		}else{
			content = content + ":";
		}
		resourceUnion.setContent(content);
		resourceUnionVo.setResourceUnion(resourceUnion);
		return resourceUnionVo;
	}
	
	/**
	 * Generate Create Lead Command Description
	 * @param request
	 * @return
	 */
	public ResourceUnionVo generateCreateLeadCommandDescription(
			C4CUserActionVo request) {
		ResourceUnionVo resourceUnionVo = new ResourceUnionVo();
		ResourceUnion resourceUnion = new ResourceUnion();
		resourceUnion.setType(ResourceUnionConstants.TYPE_CHAT);
		// default index: 1, could be adjust later.
		resourceUnion.setDisplayIndex(3);
		String content = "IF neccessary, Click this button to create a new Lead ";
		String customerName = dumDeepLearnProcessUtility.getCustomerNameFromRequest(request);
		if(customerName != null){
			content = content + " for customer:" + customerName;
		}else{
			content = content + ":";
		}
		resourceUnion.setContent(content);
		resourceUnionVo.setResourceUnion(resourceUnion);
		return resourceUnionVo;
	}
	
	/**
	 * Generate Create Opportunity Command Description
	 * @param request
	 * @return
	 */
	public ResourceUnionVo generateCreateOpportunityCommandDescription(
			C4CUserActionVo request) {
		ResourceUnionVo resourceUnionVo = new ResourceUnionVo();
		ResourceUnion resourceUnion = new ResourceUnion();
		resourceUnion.setType(ResourceUnionConstants.TYPE_CHAT);
		// default index: 1, could be adjust later.
		resourceUnion.setDisplayIndex(3);
		String content = "IF neccessary, Click this button to create a new Opportunity ";
		String customerName = dumDeepLearnProcessUtility.getCustomerNameFromRequest(request);
		if(customerName != null){
			content = content + " for customer:" + customerName;
		}else{
			content = content + ":";
		}
		resourceUnion.setContent(content);
		resourceUnionVo.setResourceUnion(resourceUnion);
		return resourceUnionVo;
	}
	
	/**
	 * Create Visit
	 * @param request
	 * @return
	 */
	List<ResourceUnionVo> generateCreateResult(C4CUserActionVo request){
		List<ResourceUnionVo> result = new ArrayList<ResourceUnionVo>();
		// 1: Generate [Create] Action Chat & description
		ResourceUnionVo homeCommandChatUnionVo = generateCreateCommandDescription(request);
		result.add(homeCommandChatUnionVo);
		// 2: Generate [Create] Action Button 
		ResourceUnionVo homeCommandUnionVo = dumDeepLearnProcessUtility.copyToCommandResourceUnionDirectly(request);
		homeCommandUnionVo.getResourceUnion().setDisplayIndex(2);
		result.add(homeCommandUnionVo);
		//TODO dummy Logic to decide weather need to create relative lead or oppor or sales order or donoting.
		double rand = Math.random();
		if(rand > 0.33){
			// In case create a lead
			ResourceUnionVo leadCommandChatUnionVo = generateCreateLeadCommandDescription(request);
			result.add(leadCommandChatUnionVo);
			ResourceUnionVo leadUnionVo = dumDeepLearnProcessUtility.copyToCommandResourceUnionDirectly(request);
			leadUnionVo.getResourceUnion().setAction(SapActionsConstants.CREATE);
			leadUnionVo.getResourceUnion().setTarget(SapThingTypeConstants.LEAD);
			homeCommandUnionVo.getResourceUnion().setDisplayIndex(4);
			result.add(homeCommandUnionVo);
		}
		if(rand < 0.33){
			// In case create a opportunity
			ResourceUnionVo leadCommandChatUnionVo = generateCreateOpportunityCommandDescription(request);
			result.add(leadCommandChatUnionVo);
			ResourceUnionVo leadUnionVo = dumDeepLearnProcessUtility.copyToCommandResourceUnionDirectly(request);
			leadUnionVo.getResourceUnion().setAction(SapActionsConstants.CREATE);
			leadUnionVo.getResourceUnion().setTarget(SapThingTypeConstants.OPPORTUNITY);
			homeCommandUnionVo.getResourceUnion().setDisplayIndex(4);
			result.add(homeCommandUnionVo);
		}
		
		
		return result;
	}

	@Override
	public List<ResourceUnionVo> generateResult(C4CUserActionVo request){

		String action = dumDeepLearnProcessUtility.getRequestAction(request);
		if(action!= null && action.equals(SapActionsConstants.CREATE)){
			// In case: Create Sales Order
			return generateCreateResult(request);
		}
		return null;
	}


}
