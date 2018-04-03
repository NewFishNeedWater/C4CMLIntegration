package com.sap.integration.serviceImpl.MLProcessUnion.dummyDeepLearn;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.integration.vo.requestVo.C4CUserActionVo;
import com.sap.integration.vo.responseVo.ResourceUnionVo;

@Service
public class AccountDeepLearnProcessUnion extends DumDeepLearnProcessUnion{
	
	public List<ResourceUnionVo> generateResult(C4CUserActionVo request){
		return null;
	}

}
