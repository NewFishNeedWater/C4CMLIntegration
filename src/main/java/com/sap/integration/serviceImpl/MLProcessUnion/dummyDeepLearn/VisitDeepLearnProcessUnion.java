package com.sap.integration.serviceImpl.MLProcessUnion.dummyDeepLearn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

/**
 * Process Union For ThingType "Visit"
 * @author I043125
 *
 */
@Service
public class VisitDeepLearnProcessUnion extends DumDeepLearnProcessUnion{
	
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
	private ResourceUnionVo generateCreateLeadCommandDescription(
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
	private ResourceUnionVo generateCreateOpportunityCommandDescription(
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
	private List<ResourceUnionVo> generateCreateResult(C4CUserActionVo request){
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
			leadUnionVo.getResourceUnion().setDisplayIndex(4);
			result.add(leadUnionVo);
		}
		if(rand < 0.33){
			// In case create a opportunity
			ResourceUnionVo oppCommandChatUnionVo = generateCreateOpportunityCommandDescription(request);
			result.add(oppCommandChatUnionVo);
			ResourceUnionVo oppUnionVo = dumDeepLearnProcessUtility.copyToCommandResourceUnionDirectly(request);
			oppUnionVo.getResourceUnion().setAction(SapActionsConstants.CREATE);
			oppUnionVo.getResourceUnion().setTarget(SapThingTypeConstants.OPPORTUNITY);
			oppUnionVo.getResourceUnion().setDisplayIndex(4);
			result.add(oppUnionVo);
		}

        ResourceUnionVo infoUnion = new ResourceUnionVo();

        ResourceUnion resourceUnion = new ResourceUnion();
        infoUnion.setResourceUnion(resourceUnion);
        resourceUnion.setType(ResourceUnionConstants.TYPE_TEXTINFO);
        resourceUnion.setDisplayIndex(5);
        List<ResourceContent> resourceContentList = new ArrayList<>();
        resourceUnion.setResourceContents(resourceContentList);
        ResourceContent content = dumDeepLearnProcessUtility.createResourceContent(new BigDecimal(0.53),"text","Sales Order Successful rate since 2018:" + (int)Math.random()*100 + "%");
        resourceContentList.add(content);

        String customerName = dumDeepLearnProcessUtility.getCustomerNameFromRequest(request);
        if(null!=customerName){
            ResourceContent content1 = dumDeepLearnProcessUtility.createResourceContent(new BigDecimal(0.91),"title",customerName);
            resourceContentList.add(content1);
        }

        ResourceContent content2 = dumDeepLearnProcessUtility.createResourceContent(new BigDecimal(0.53),"text","Mostly visited products");
        resourceContentList.add(content2);
        Product product2 = dumDeepLearnProcessUtility.getRandomProduct(1).get(0);
        ResourceContent content3 = dumDeepLearnProcessUtility.createResourceContent(new BigDecimal(0.65),"text","Material:"+product2.getMaterial() + "Number of quoted:"+product2.getNumOfQuoted());
        resourceContentList.add(content3);

        Product product = dumDeepLearnProcessUtility.getRandomProduct(1).get(0);
        ResourceContent content4 = dumDeepLearnProcessUtility.createResourceContent(new BigDecimal(0.65),"text","Material:"+product.getMaterial() + "Number of quoted:"+product.getNumOfQuoted());
        resourceContentList.add(content4);

        Product product1 = dumDeepLearnProcessUtility.getRandomProduct(1).get(0);
        ResourceContent content5 = dumDeepLearnProcessUtility.createResourceContent(new BigDecimal(0.65),"text","Material:"+product1.getMaterial() + "Number of quoted:"+product1.getNumOfQuoted());
        resourceContentList.add(content5);
        result.add(infoUnion);

		return result;
	}


	@Override
	public List<ResourceUnionVo> generateResult(C4CUserActionVo request){

		String action = dumDeepLearnProcessUtility.getRequestAction(request);
		if(action!= null && action.equals(SapActionsConstants.CREATE)){
			// In case: Create Visit
			return generateCreateResult(request);
		}
		return null;
	}

}
