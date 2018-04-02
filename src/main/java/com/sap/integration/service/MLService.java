package com.sap.integration.service;

import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.responseVo.C4CUserActionResponse;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessResponse;

public interface MLService {

    C4CUserActionResponse getDemoResponse();
    
    C4CUserActionVo convertNLPToUserActionRequest(
			NaturalLanProcessResponse naturalLanProcessResponse) ;
}
