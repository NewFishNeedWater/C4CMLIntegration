package com.sap.integration.serviceImpl;

import com.sap.integration.constants.SapActionsConstants;
import com.sap.integration.constants.SapParameterConstants;
import com.sap.integration.constants.SapThingTypeConstants;
import com.sap.integration.model.CommandPara;
import com.sap.integration.service.NaturalLanProcessService;
import com.sap.integration.utils.HttpRequestUtils;
import com.sap.integration.vo.requestVo.naturalLanProcess.NaturalLanProcessRequest;
import com.sap.integration.vo.responseVo.naturalLanProcess.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NaturalLanProcessServiceBean implements NaturalLanProcessService {

    private static Logger logger = LoggerFactory
            .getLogger(NaturalLanProcessServiceBean.class);

    public NaturalLanProcessResponse getNLResponse(NaturalLanProcessRequest request){

        JSONObject jsonRequest = JSONObject.fromObject(request);

        // Call Recast.ai NLP process
        String sUrl = "https://api.recast.ai/build/v1/dialog";

        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Authorization", "Token cc808a4996ec489278238113fa40d973");

        JSONObject jsonResult = HttpRequestUtils.httpPost(sUrl, jsonRequest, headerMap, "getNLPProcessResult");

        logger.warn("getRelatedAction--Response to external:"
                + JSONObject.fromObject(jsonResult).toString());

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
        classMap.put("results", NaturalLanProcessResult.class);
        classMap.put("conversation", NaturalLanProcessConversation.class);
        classMap.put("nlp", NaturalLanProcessnlp.class);
        classMap.put("entities", NaturalLanProcessEntityUnion.class);
        classMap.put("memory", NaturalLanProcessMemory.class);

        NaturalLanProcessResponse naturalLanProcessResponse = (NaturalLanProcessResponse) JSONObject
                .toBean(jsonResult, NaturalLanProcessResponse.class,
                        classMap);

        return naturalLanProcessResponse;
    }

    public List<CommandPara> parseOperationParaFromNLPResponse(
            NaturalLanProcessResponse naturalLanProcessResponse) {
        List<CommandPara> resultList = new ArrayList<CommandPara>();
        // Entities retrieved from NLP AI Channel
        NaturalLanProcessEntityUnion entities = naturalLanProcessResponse
                .getResults().getNlp().getEntities();
        if (entities == null) {
            // In case no entities could be retrieved from NLP process
            return null;
        }
        List<NaturalLanProcessEntity> customerList = entities.getCustomer();
        if (customerList != null && customerList.size() > 0) {
            for (NaturalLanProcessEntity customerEntity : customerList) {
                CommandPara commandPara = new CommandPara();
                commandPara.setParaName(SapParameterConstants.CUSTOMERNAME);
                commandPara.setParaValue(customerEntity.getValue());
                resultList.add(commandPara);
            }
        }

        List<NaturalLanProcessEntity> personList = entities.getPerson();
        if (personList != null && personList.size() > 0) {
            for (NaturalLanProcessEntity personEntity : personList) {
                CommandPara commandPara = new CommandPara();
                commandPara.setParaName(SapParameterConstants.CUSTOMERNAME);
                commandPara.setParaValue(personEntity.getValue());
                resultList.add(commandPara);
            }
        }
        return resultList;
    }


    private String parseSapCommandFromTargetCore(String rawCommand) {
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


    public String parseCommandFromNLPResponse(
            NaturalLanProcessResponse naturalLanProcessResponse) {
        // Entities retrieved from NLP AI Channel
        NaturalLanProcessEntityUnion entities = naturalLanProcessResponse
                .getResults().getNlp().getEntities();
        if (entities == null) {
            // In case no entities could be retrieved from NLP process
            return null;
        }
        List<NaturalLanProcessEntity>  commandList = entities.getCommand();
        if(commandList != null && commandList.size() > 0){
            return parseSapCommandFromTargetCore(commandList.get(0).getValue());
        }else{
            // TODO handling process for not get command
        }
        return null;
    }


    public String parseTargetFromNLPResponse(
            NaturalLanProcessResponse naturalLanProcessResponse) {
        // Entities retrieved from NLP AI Channel
        NaturalLanProcessEntityUnion entities = naturalLanProcessResponse
                .getResults().getNlp().getEntities();
        if (entities == null) {
            // In case no entities could be retrieved from NLP process
            return null;
        }
        List<NaturalLanProcessEntity> commandList = entities.getOrdertype();
        if (commandList != null && commandList.size() > 0) {
            return parseThingTypeFromOrderTypeCore(commandList.get(0).getValue());
        } else {
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
    private String parseThingTypeFromOrderTypeCore(String rawTarget) {
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
