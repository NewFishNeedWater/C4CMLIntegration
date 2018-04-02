package com.sap.integration.serviceImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.sap.integration.constants.SapActionsConstants;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessEntity;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessEntityUnion;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessResponse;

@Service
public class NaturalLanProcessCommandServiceBean {

	/**
	 * parse C4C "Target" key parameter from NLP Process output response
	 * 
	 * @return
	 */
	public String parseCommandFromNLPResponse(
			NaturalLanProcessResponse naturalLanProcessResponse) {
		// Entities retrieved from NLP AI Channel
		NaturalLanProcessEntityUnion entities = naturalLanProcessResponse
				.getResults().getNlp().getEntities();
		if (entities == null) {
			// In case no entities could be retrieved from NLP process
			return null;
		}
		ArrayList<NaturalLanProcessEntity>  commandList = entities.getCommand();
		if(commandList != null && commandList.size() > 0){
			return parseSapCommandFromTargetCore(commandList.get(0).getValue());
		}else{
			// TODO handling process for not get command
		}
		return null;
	}

	/**
	 * Core Logic to parse C4C Thing type id from target from natural word.
	 * 
	 * @param rawTarget
	 * @return
	 */
	public String parseSapCommandFromTargetCore(String rawCommand) {
		// Mapping to Create
		if (rawCommand.toUpperCase().contains("create".toUpperCase())) {
			return SapActionsConstants.CREATE;
		}
		if (rawCommand.equalsIgnoreCase("new")) {
			return SapActionsConstants.CREATE;
		}
		if (rawCommand.equalsIgnoreCase("plan")) {
			return SapActionsConstants.CREATE;
		}
		if (rawCommand.equalsIgnoreCase("do")) {
			return SapActionsConstants.CREATE;
		}

		if (rawCommand.equalsIgnoreCase("update")) {
			return SapActionsConstants.EDIT;
		}
		if (rawCommand.equalsIgnoreCase("modify")) {
			return SapActionsConstants.EDIT;
		}

		// TODO......
		
		return null;
	}


	
}
