package com.sap.integration.web.machineLearning;

import com.sap.integration.model.LogInfo;
import com.sap.integration.service.LogInfoService;
import com.sap.integration.service.MLService;
import com.sap.integration.utils.HttpRequestUtils;
import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.requestVo.naturalLanProcess.NaturalLanProcessRequest;
import com.sap.integration.vo.responseVo.C4CUserActionResponse;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessEntity;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessIntent;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessMessage;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joeyy
 *
 */
@Api(value = "C4C ML Integration", tags = "{C4C Request Handler}")
@RestController
public class C4CControllerForML {

	@Autowired
	MLService mlService;

	@Autowired
	LogInfoService logInfoService;

	private static Logger logger = LoggerFactory
			.getLogger(C4CControllerForML.class);

	@ApiOperation(value = "User Action", notes = "Get Related User Actions info from ML", httpMethod = "POST")
	@RequestMapping(value = "/getRelatedAction", method = RequestMethod.POST)
	public C4CUserActionResponse getRelatedAction(
			@RequestBody @ApiParam(name = "User Command", value = "Received User Command Info from C4C", required = true) C4CUserActionVo vo) {

		C4CUserActionResponse response;

		logger.warn("getRelatedAction--Request from external:"
				+ JSONObject.fromObject(vo).toString());

		LogInfo log = new LogInfo();

		log.setApiName("Get Related Action");

		log.setApiType("IN");

		log.setStartTime(new Date());

		log.setRequest(JSONObject.fromObject(vo).toString());

		// for demo response
		response = mlService.getDemoResponse();

		logger.warn("getRelatedAction--Response to external:"
				+ JSONObject.fromObject(response).toString());

		log.setEndTime(new Date());

		log.setResponse(JSONObject.fromObject(response).toString());

		logInfoService.saveLog(log);

		return response;

	}
	
	@ApiOperation(value = "AI API Wrapper", notes = "Get Whole AI Process Result", httpMethod = "POST")
	@RequestMapping(value = "/getAIProcessResult", method = RequestMethod.POST)
	public C4CUserActionResponse getAIProcessResult(
			@RequestBody @ApiParam(name = "NLP API", value = "Get Whole AI Process Result", required = true) NaturalLanProcessRequest request) {
		NaturalLanProcessResponse nlpResponse = getNLPProcessResult(request);
		C4CUserActionVo deepLearnRequest = mlService.convertNLPToUserActionRequest(nlpResponse);
		C4CUserActionResponse response = this.getRelatedAction(deepLearnRequest);
		return response;
	}

	@ApiOperation(value = "NLP API", notes = "Get Natural Lanuage Process Result", httpMethod = "POST")
	@RequestMapping(value = "/getNLPProcessResult", method = RequestMethod.POST)
	public NaturalLanProcessResponse getNLPProcessResult(
			@RequestBody @ApiParam(name = "NLP API", value = "Get Natural Lanuage Process Result", required = true) NaturalLanProcessRequest request) {

		NaturalLanProcessResponse naturalLanProcessResponse;
		
		JSONObject jsonRequest = JSONObject.fromObject(request);

		logger.warn("getNLPProcessResult--Request from external:"
				+ jsonRequest.toString());

		LogInfo log = new LogInfo();

		log.setApiName("Get NLP Process Result");

		log.setApiType("IN");

		log.setStartTime(new Date());

		log.setRequest(jsonRequest.toString());

		// Call Recast.ai NLP process
		String sUrl = "https://api.recast.ai/build/v1/dialog";
		
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization", "Token cc808a4996ec489278238113fa40d973");
		
		JSONObject jsonResult = HttpRequestUtils.httpPost(sUrl, jsonRequest, headerMap, "getNLPProcessResult");

		logger.warn("getRelatedAction--Response to external:"
				+ JSONObject.fromObject(jsonResult).toString());

		log.setEndTime(new Date());

		log.setResponse(JSONObject.fromObject(jsonResult).toString());

		logInfoService.saveLog(log);
		
		@SuppressWarnings("rawtypes")
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("messages",
				NaturalLanProcessMessage.class);
		classMap.put("intents",
				NaturalLanProcessIntent.class);
		classMap.put("command",
				NaturalLanProcessEntity.class);
		classMap.put("number",
				NaturalLanProcessEntity.class);
		classMap.put("ordertype",
				NaturalLanProcessEntity.class);
		classMap.put("customer",
				NaturalLanProcessEntity.class);		
	
		naturalLanProcessResponse = (NaturalLanProcessResponse) JSONObject
				.toBean(jsonResult, NaturalLanProcessResponse.class,
						classMap);

		return naturalLanProcessResponse;

	}

}