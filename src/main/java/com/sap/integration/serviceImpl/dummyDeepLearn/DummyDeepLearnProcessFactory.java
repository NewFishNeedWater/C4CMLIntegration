package com.sap.integration.serviceImpl.dummyDeepLearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.integration.constants.SapThingTypeConstants;
import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.responseVo.C4CUserActionResponse;

/**
 * Dummy Deep Learn Process Factory to generate process union class by Target (Thing Type)
 * @author I043125
 *
 */
@Service
public class DummyDeepLearnProcessFactory {
	
	@Autowired
	private AccountDeepLearnProcessUnion accountDeepLearnProcessUnion;
	
	@Autowired
	private LeadDeepLearnProcessUnion leadDeepLearnProcessUnion;
	
	@Autowired
	private OpportunityDeepLearnProcessUnion opportunityDeepLearnProcessUnion;
	
	@Autowired
	private RouteDeepLearnProcessUnion routeDeepLearnProcessUnion;
	
	@Autowired
	private SalesOrderDeepLearnProcessUnion salesOrderDeepLearnProcessUnion;
	

	@Autowired
	private VisitDeepLearnProcessUnion visitDeepLearnProcessUnion;
	
	/**
	 * Main Entry to generate Dummy Deep Learn Response
	 * @param request
	 * @return
	 */
	public C4CUserActionResponse generateResultEntry(C4CUserActionVo request){
		DumDeepLearnProcessUnion deepLearnProcessUnion = getProcessUnion(request);
		C4CUserActionResponse userActionResponse;
		userActionResponse = new C4CUserActionResponse();
		userActionResponse.setResourceUnionList(deepLearnProcessUnion.generateResult(request));
		return userActionResponse;
	}
	
	/**
	 * Get Dummy Deep Learn Process Union class by analyze request Thing Type.
	 * @param request
	 * @return
	 */
	public DumDeepLearnProcessUnion getProcessUnion(C4CUserActionVo request){
		if(request.getCommand().getTarget().equals(SapThingTypeConstants.ACCOUNT)){
			return accountDeepLearnProcessUnion;
		}
		if(request.getCommand().getTarget().equals(SapThingTypeConstants.APPOINTMENT)){
			return accountDeepLearnProcessUnion;
		}
		if(request.getCommand().getTarget().equals(SapThingTypeConstants.CAMPAIGN)){
			return accountDeepLearnProcessUnion;
		}
		if(request.getCommand().getTarget().equals(SapThingTypeConstants.CONTACT)){
			return accountDeepLearnProcessUnion;
		}
		if(request.getCommand().getTarget().equals(SapThingTypeConstants.LEAD)){
			return leadDeepLearnProcessUnion;
		}
		if(request.getCommand().getTarget().equals(SapThingTypeConstants.MATERIAL)){
			return leadDeepLearnProcessUnion;
		}
		
		if(request.getCommand().getTarget().equals(SapThingTypeConstants.SALES_ORDER)){
			return salesOrderDeepLearnProcessUnion;
		}
		if(request.getCommand().getTarget().equals(SapThingTypeConstants.TASK)){
			return visitDeepLearnProcessUnion;
		}
		if(request.getCommand().getTarget().equals(SapThingTypeConstants.VISIT)){
			return visitDeepLearnProcessUnion;
		}
		
		//TODO default Process Union
		return salesOrderDeepLearnProcessUnion;
	}
	
	

}
