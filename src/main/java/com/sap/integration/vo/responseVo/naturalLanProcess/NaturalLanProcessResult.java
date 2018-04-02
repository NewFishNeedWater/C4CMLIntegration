package com.sap.integration.vo.responseVo.naturalLanProcess;

import java.util.ArrayList;

public class NaturalLanProcessResult {
	
	private ArrayList<NaturalLanProcessMessage> messages = new ArrayList<NaturalLanProcessMessage>();
	
	private NaturalLanProcessConversation conversation;
	
	private NaturalLanProcessnlp nlp;

	public ArrayList<NaturalLanProcessMessage> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<NaturalLanProcessMessage> messages) {
		this.messages = messages;
	}

	public NaturalLanProcessConversation getConversation() {
		return conversation;
	}

	public void setConversation(NaturalLanProcessConversation conversation) {
		this.conversation = conversation;
	}

	public NaturalLanProcessnlp getNlp() {
		return nlp;
	}

	public void setNlp(NaturalLanProcessnlp nlp) {
		this.nlp = nlp;
	}

}
