package com.sap.integration.vo.requestVo.naturalLanProcess;

public class NaturalLanProcessRequest {
	
	private NaturalLanProcessReqMessage message;
	
	private String conversation_id;

	public NaturalLanProcessReqMessage getMessage() {
		return message;
	}

	public void setMessage(NaturalLanProcessReqMessage message) {
		this.message = message;
	}

	public String getConversation_id() {
		return conversation_id;
	}

	public void setConversation_id(String conversation_id) {
		this.conversation_id = conversation_id;
	}

}
