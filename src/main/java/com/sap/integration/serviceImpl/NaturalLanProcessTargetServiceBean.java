package com.sap.integration.serviceImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.sap.integration.constants.SapThingTypeConstants;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessEntity;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessEntityUnion;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessResponse;

@Service
public class NaturalLanProcessTargetServiceBean {

	/**
	 * parse C4C "Target" key parameter from NLP Process output response
	 * 
	 * @return
	 */
	public String parseTargetFromNLPResponse(
			NaturalLanProcessResponse naturalLanProcessResponse) {
		// Entities retrieved from NLP AI Channel
		NaturalLanProcessEntityUnion entities = naturalLanProcessResponse
				.getResults().getNlp().getEntities();
		if (entities == null) {
			// In case no entities could be retrieved from NLP process
			return null;
		}
		ArrayList<NaturalLanProcessEntity>  commandList = entities.getOrdertype();
		if(commandList != null && commandList.size() > 0){
			return parseThingTypeFromOrderTypeCore(commandList.get(0).getValue());
		}else{
			// TODO handling process for not get order type
		}
		return null;
	}

	/**
	 * Core Logic to parse C4C Thing type id from target from natural word.
	 * 
	 * @param rawTarget
	 * @return
	 */
	public String parseThingTypeFromOrderTypeCore(String rawTarget) {
		// Mapping to Thing-type: sales order
		if (rawTarget.toUpperCase().contains("sales order".toUpperCase())) {
			return SapThingTypeConstants.SALES_ORDER;
		}
		if (rawTarget.toUpperCase().contains("salesorder".toUpperCase())) {
			return SapThingTypeConstants.SALES_ORDER;
		}

		// Mapping to Thing-type: opportunity
		if (rawTarget.toUpperCase().contains("oppor".toUpperCase())) {
			return SapThingTypeConstants.OPPORTUNITY;
		}

		// Mapping to Thing-type: lead
		if (rawTarget.toUpperCase().contains("lead".toUpperCase())) {
			return SapThingTypeConstants.LEAD;
		}

		// Mapping to Thing-type: account / customer
		if (rawTarget.toUpperCase().contains("account".toUpperCase())) {
			return SapThingTypeConstants.ACCOUNT;
		}
		if (rawTarget.toUpperCase().contains("customer".toUpperCase())) {
			return SapThingTypeConstants.ACCOUNT;
		}

		if (rawTarget.toUpperCase().contains("appoint".toUpperCase())) {
			return SapThingTypeConstants.APPOINTMENT;
		}

		if (rawTarget.toUpperCase().contains("campaign".toUpperCase())) {
			return SapThingTypeConstants.CAMPAIGN;
		}

		if (rawTarget.toUpperCase().contains("contact".toUpperCase())) {
			return SapThingTypeConstants.CONTACT;
		}

		if (rawTarget.toUpperCase().contains("quote".toUpperCase())) {
			return SapThingTypeConstants.QUOTE;
		}

		if (rawTarget.toUpperCase().contains("route".toUpperCase())) {
			return SapThingTypeConstants.ROUTE;
		}

		if (rawTarget.toUpperCase().contains("task".toUpperCase())) {
			return SapThingTypeConstants.TASK;
		}

		// Mapping to Thing-type: visit
		if (rawTarget.toUpperCase().contains("visit".toUpperCase())) {
			return SapThingTypeConstants.VISIT;
		}
		
		// TODO Default Thing-type??
		return SapThingTypeConstants.SALES_ORDER;
	}

}
