package com.sap.integration.service;

import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.responseVo.C4CUserActionResponse;

public interface DeepLearningService {

    C4CUserActionResponse generateResultEntry(C4CUserActionVo deepLearnRequest);
}
