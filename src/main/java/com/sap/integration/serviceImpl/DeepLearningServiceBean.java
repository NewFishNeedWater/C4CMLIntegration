package com.sap.integration.serviceImpl;

import com.sap.integration.service.DeepLearningService;
import com.sap.integration.serviceImpl.MLProcessUnion.dummyDeepLearn.DummyDeepLearnProcessFactory;
import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.responseVo.C4CUserActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeepLearningServiceBean implements DeepLearningService {

    @Autowired
    DummyDeepLearnProcessFactory dummyDeepLearnProcessFactory;

    public C4CUserActionResponse generateResultEntry(C4CUserActionVo deepLearnRequest){

        return dummyDeepLearnProcessFactory.generateResultEntry(deepLearnRequest);
    }

}
