package com.sap.integration.serviceImpl;

import com.sap.integration.constants.Constants;
import com.sap.integration.model.CommandPara;
import com.sap.integration.service.MLService;
import com.sap.integration.service.NaturalLanProcessService;
import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.requestVo.Command;
import com.sap.integration.vo.responseVo.C4CUserActionResponse;
import com.sap.integration.vo.responseVo.ResourceContent;
import com.sap.integration.vo.responseVo.ResourceUnion;
import com.sap.integration.vo.responseVo.ResourceUnionVo;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MLServiceBean implements MLService {

	private static Logger logger = LoggerFactory.getLogger(MLServiceBean.class);

	@Autowired
	private NaturalLanProcessService naturalLanProcessService;

	public C4CUserActionResponse getDemoResponse() {

		C4CUserActionResponse response = new C4CUserActionResponse();
		String sample = Constants.DEMO_RESPONSE_FOR_C4C_ACTION;

		Map<String, Class> childClass = new HashMap<>();

		childClass.put("commandParas", CommandPara.class);
		childClass.put("resourceUnion", ResourceUnion.class);
		childClass.put("resourceContents", ResourceContent.class);

		List<ResourceUnionVo> resourceUnions = (List<ResourceUnionVo>) JSONArray
				.toList(JSONArray.fromObject(sample), ResourceUnionVo.class,
						childClass);
		response.setResourceUnionList(resourceUnions);

		logger.warn("demo response---------"
				+ JSONObject.fromObject(response).toString());

		return response;

	}

	/**
	 * Main Conversation Logic for Converting NLP Process output response into
	 * Deep Learning Channel input request.
	 * 
	 * @param naturalLanProcessResponse
	 *            : NLP process response
	 * @return C4CUserActionVo: Deep Learning AI Channel input request.
	 */
	public C4CUserActionVo convertNLPToUserActionRequest(
			NaturalLanProcessResponse naturalLanProcessResponse) {
		C4CUserActionVo result = new C4CUserActionVo();
		Command command = new Command();
		String target = naturalLanProcessService.parseTargetFromNLPResponse(naturalLanProcessResponse);
		String action = naturalLanProcessService.parseCommandFromNLPResponse(naturalLanProcessResponse);
		command.setAction(action);
		command.setTarget(target);
		List<CommandPara> commandParaList = naturalLanProcessService.parseOperationParaFromNLPResponse(naturalLanProcessResponse);
		command.setCommandParaList(commandParaList);
		result.setCommand(command);
		return result;

	}
	
	

}
