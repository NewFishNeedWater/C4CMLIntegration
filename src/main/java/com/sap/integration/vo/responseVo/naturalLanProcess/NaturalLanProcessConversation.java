package com.sap.integration.vo.responseVo.naturalLanProcess;

public class NaturalLanProcessConversation {
	
	protected String id;
	
	protected String lanuage;

	protected NaturalLanProcessMemory memory;
	
	protected String skill;
	
	protected int skill_occurences;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLanuage() {
		return lanuage;
	}

	public void setLanuage(String lanuage) {
		this.lanuage = lanuage;
	}

	public NaturalLanProcessMemory getMemory() {
		return memory;
	}

	public void setMemory(NaturalLanProcessMemory memory) {
		this.memory = memory;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getSkill_occurences() {
		return skill_occurences;
	}

	public void setSkill_occurences(int skill_occurences) {
		this.skill_occurences = skill_occurences;
	}

}
