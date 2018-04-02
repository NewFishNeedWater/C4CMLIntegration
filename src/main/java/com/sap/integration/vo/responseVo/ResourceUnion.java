package com.sap.integration.vo.responseVo;

import com.sap.integration.model.CommandPara;

import java.math.BigDecimal;
import java.util.List;

public class ResourceUnion {

    private String type;
    private String target;
    private Integer displayIndex;
    private String action;
    private BigDecimal hitRate;
    private List<String> contents;
    private List<CommandPara> commandParas;
    private List<ResourceContent> resourceContents;
    private String content;


    public List<ResourceContent> getResourceContents() {
        return resourceContents;
    }

    public void setResourceContents(List<ResourceContent> resourceContents) {
        this.resourceContents = resourceContents;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigDecimal getHitRate() {
        return hitRate;
    }

    public void setHitRate(BigDecimal hitRate) {
        this.hitRate = hitRate;
    }

    public List<CommandPara> getCommandParas() {
        return commandParas;
    }

    public void setCommandParas(List<CommandPara> commandParas) {
        this.commandParas = commandParas;
    }
}
