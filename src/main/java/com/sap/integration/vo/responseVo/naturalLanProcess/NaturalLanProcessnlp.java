package com.sap.integration.vo.responseVo.naturalLanProcess;

import java.util.ArrayList;



public class NaturalLanProcessnlp {
	
	private String uuid;
	
	private String source;
	
	private ArrayList<NaturalLanProcessIntent> intents = new ArrayList<NaturalLanProcessIntent>();
	
	private NaturalLanProcessEntityUnion entities;
	
	private String act;
	
	private String type;
	
	private String sentiment;
	
	private String lanuage;
	
	private String processing_language;
	
	private String version;
	
	private String timestamp;
	
	private int status;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public ArrayList<NaturalLanProcessIntent> getIntents() {
		return intents;
	}

	public void setIntents(ArrayList<NaturalLanProcessIntent> intents) {
		this.intents = intents;
	}

	public NaturalLanProcessEntityUnion getEntities() {
		return entities;
	}

	public void setEntities(NaturalLanProcessEntityUnion entities) {
		this.entities = entities;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public String getLanuage() {
		return lanuage;
	}

	public void setLanuage(String lanuage) {
		this.lanuage = lanuage;
	}

	public String getProcessing_language() {
		return processing_language;
	}

	public void setProcessing_language(String processing_language) {
		this.processing_language = processing_language;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
