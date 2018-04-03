package com.sap.integration.web.machineLearning;

import com.sap.integration.model.LogInfo;
import com.sap.integration.service.DeepLearningService;
import com.sap.integration.service.LogInfoService;
import com.sap.integration.service.MLService;
import com.sap.integration.service.NaturalLanProcessService;
import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.requestVo.naturalLanProcess.NaturalLanProcessRequest;
import com.sap.integration.vo.responseVo.C4CUserActionResponse;
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

/**
 * @author Joeyy
 */
@Api(value = "C4C ML Integration", tags = "{C4C Request Handler}")
@RestController
public class C4CControllerForML {

    @Autowired
    MLService mlService;

    @Autowired
    LogInfoService logInfoService;

    @Autowired
    DeepLearningService deepLearningService;

    @Autowired
    NaturalLanProcessService naturalLanProcessService;

    private static Logger logger = LoggerFactory
            .getLogger(C4CControllerForML.class);

    @ApiOperation(value = "AI API Wrapper", notes = "Get Whole AI Process Result", httpMethod = "POST")
    @RequestMapping(value = "/getAIProcessResult", method = RequestMethod.POST)
    public C4CUserActionResponse getAIProcessResult(
            @RequestBody @ApiParam(name = "NLP API", value = "Get Whole AI Process Result", required = true) NaturalLanProcessRequest request) {

        //get nature language response from recast API
        NaturalLanProcessResponse nlpResponse = naturalLanProcessService.getNLResponse(request);
        //convert the NLP response to deep learning API request
        C4CUserActionVo deepLearnRequest = mlService.convertNLPToUserActionRequest(nlpResponse);
        //get response from deep learning API
        C4CUserActionResponse response = deepLearningService.generateResultEntry(deepLearnRequest);


        //save log
        logger.warn("getRelatedAction--Request from external:"
                + JSONObject.fromObject(request).toString());

        LogInfo log = new LogInfo();

        log.setApiName("getAIProcessResult");

        log.setApiType("IN");

        log.setStartTime(new Date());

        log.setRequest(JSONObject.fromObject(request).toString());

        logger.warn("getRelatedAction--Response to external:"
                + JSONObject.fromObject(response).toString());

        log.setEndTime(new Date());

        log.setResponse(JSONObject.fromObject(response).toString());

        logInfoService.saveLog(log);

        return response;
    }


}