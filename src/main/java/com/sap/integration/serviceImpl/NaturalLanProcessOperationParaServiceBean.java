package com.sap.integration.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.integration.constants.SapParameterConstants;
import com.sap.integration.model.CommandPara;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessEntity;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessEntityUnion;
import com.sap.integration.vo.responseVo.naturalLanProcess.NaturalLanProcessResponse;

@Service
public class NaturalLanProcessOperationParaServiceBean {
	
	/**
	 * parse C4C "Target" key parameter from NLP Process output response
	 * 
	 * @return
	 */
	public List<CommandPara> parseCommandFromNLPResponse(
			NaturalLanProcessResponse naturalLanProcessResponse) {
		List<CommandPara> resultList = new ArrayList<CommandPara>();
		// Entities retrieved from NLP AI Channel
		NaturalLanProcessEntityUnion entities = naturalLanProcessResponse
				.getResults().getNlp().getEntities();
		if (entities == null) {
			// In case no entities could be retrieved from NLP process
			return null;
		}
		ArrayList<NaturalLanProcessEntity>  customerList = entities.getCustomer();
		if(customerList != null && customerList.size() > 0){
			for(NaturalLanProcessEntity customerEntity:customerList){
				CommandPara commandPara = new CommandPara();
				commandPara.setParaName(SapParameterConstants.CUSTOMERNAME);
				commandPara.setParaValue(customerEntity.getValue());
				resultList.add(commandPara);
			}
		}
		return resultList;
	}

	
}
