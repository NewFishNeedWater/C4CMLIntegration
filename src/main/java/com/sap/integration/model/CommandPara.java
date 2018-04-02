package com.sap.integration.model;

import java.math.BigDecimal;

public class CommandPara {

    private String paraName;
    private String paraValue;
    private BigDecimal hitRate;
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public String getParaValue() {
        return paraValue;
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
    }

    public BigDecimal getHitRate() {
        return hitRate;
    }

    public void setHitRate(BigDecimal hitRate) {
        this.hitRate = hitRate;
    }
}
