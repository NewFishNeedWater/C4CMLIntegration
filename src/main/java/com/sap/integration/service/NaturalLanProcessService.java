package com.sap.integration.service;

import com.sap.integration.model.CommandPara;
import com.sap.integration.vo.requestVo.naturalLanProcess.NaturalLanProcessRequest;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessResponse;

import java.util.List;

public interface NaturalLanProcessService {

    /**@function to Call Recast.ai NLP process
     * @return NaturalLanProcessResponse
     */
    NaturalLanProcessResponse getNLResponse(NaturalLanProcessRequest request);

    /**
     * parse C4C "Target" key parameter from NLP Process output response
     *
     * @return
     */
    String parseCommandFromNLPResponse(
            NaturalLanProcessResponse naturalLanProcessResponse);

    /**
     * parse C4C "Target" key parameter from NLP Process output response
     *
     * @return
     */
    List<CommandPara> parseOperationParaFromNLPResponse(
            NaturalLanProcessResponse naturalLanProcessResponse);

    /**
     * parse C4C "Target" key parameter from NLP Process output response
     *
     * @return
     */
    String parseTargetFromNLPResponse(
            NaturalLanProcessResponse naturalLanProcessResponse);


}
