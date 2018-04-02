package com.sap.integration.web.machineLearning;


import com.sap.integration.model.LogInfo;
import com.sap.integration.service.LogInfoService;
import com.sap.integration.service.MLService;
import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.responseVo.C4CUserActionResponse;
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
 *@author Joeyy
 *
 */
@Api(value="C4C ML Integration", tags = "{C4C Request Handler}")
@RestController
public class C4CControllerForML {




    @Autowired
    MLService mlService;

    @Autowired
    LogInfoService logInfoService;

    private static Logger logger = LoggerFactory.getLogger(C4CControllerForML.class);

    @ApiOperation(value="User Action",notes="Get Related User Actions info from ML",httpMethod = "POST")
    @RequestMapping(value = "/getRelatedAction",method = RequestMethod.POST)
    public C4CUserActionResponse getRelatedAction(@RequestBody @ApiParam(name="User Command" , value = "Received User Command Info from C4C", required = true) C4CUserActionVo vo){


        C4CUserActionResponse response;

        logger.warn("getRelatedAction--Request from external:"+ JSONObject.fromObject(vo).toString());

        LogInfo log = new LogInfo();

        log.setApiName("Get Related Action");

        log.setApiType("IN");

        log.setStartTime(new Date());

        log.setRequest(JSONObject.fromObject(vo).toString());



        //for demo response
        response = mlService.getDemoResponse();

        logger.warn("getRelatedAction--Response to external:"+JSONObject.fromObject(response).toString());

        log.setEndTime(new Date());

        log.setResponse(JSONObject.fromObject(response).toString());

        logInfoService.saveLog(log);

        return response;

    }


}