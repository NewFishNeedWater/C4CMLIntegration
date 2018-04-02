package com.sap.integration.vo.responseVo.naturalLanProcess;

public class NaturalLanProcessIntent {

	private String slug;

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	private double confidence;

	public double getConfidence() {
		return this.confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	private String description;

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
