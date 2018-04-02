package com.sap.integration.vo.responseVo.naturalLanProcess;


public class NaturalLanProcessEntity {
	
	 private String value;
	 
	 private String raw;
	 
	 private double confidence;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

}
