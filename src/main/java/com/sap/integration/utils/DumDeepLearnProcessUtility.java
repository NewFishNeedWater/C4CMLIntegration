package com.sap.integration.utils;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.integration.constants.ResourceUnionConstants;
import com.sap.integration.constants.SapParameterConstants;
import com.sap.integration.model.CommandPara;
import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.responseVo.ResourceUnion;
import com.sap.integration.vo.responseVo.ResourceUnionVo;

@Service
public class DumDeepLearnProcessUtility {
	
	/**
	 * Generate "Command" Type resource union from request by directly copy.
	 * @param request
	 * @return
	 */
	public ResourceUnionVo copyToCommandResourceUnionDirectly(C4CUserActionVo request){
		ResourceUnionVo resourceUnionVo = new ResourceUnionVo();
		ResourceUnion resourceUnion = new ResourceUnion();
		resourceUnion.setType(ResourceUnionConstants.TYPE_COMMAND);
		// Default: 1 will be updated later
		resourceUnion.setDisplayIndex(1);
		resourceUnion.setAction(request.getCommand().getAction());
		resourceUnion.setTarget(request.getCommand().getTarget());
		//resourceUnion.setHitRate(0.8);
		List<CommandPara> commandParas = request.getCommand().getCommandParaList(); 
		resourceUnion.setCommandParas(commandParas);
		resourceUnionVo.setResourceUnion(resourceUnion);
		return resourceUnionVo;
	}
	
	public String getCustomerNameFromRequest(C4CUserActionVo request){
		CommandPara commandPara = getParaFromRequest(request, SapParameterConstants.CUSTOMERNAME);
		if(commandPara == null){
			return null;
		}
		return commandPara.getParaValue();
	}
	
	public String getRequestAction(C4CUserActionVo request){
		if(request.getCommand().getAction() == null)
			return null;
		return request.getCommand().getAction();
	}
	
	/**
	 * Get & Filter the CommandPara by para name from deep learn request.
	 * @param request
	 * @param paraName
	 * @return CommandPara
	 */
	public CommandPara getParaFromRequest(C4CUserActionVo request, String paraName){
		List<CommandPara> commandParaList = request.getCommand().getCommandParaList();
		if(commandParaList == null || commandParaList.size() < 1){
			return null;
		}
		for(CommandPara commandPara: commandParaList){
			if(paraName.equals(commandPara)){
				return commandPara;
			}
		}
		return null;
	}

}
