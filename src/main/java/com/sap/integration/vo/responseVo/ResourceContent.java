package com.sap.integration.vo.responseVo;

import java.math.BigDecimal;

public class ResourceContent {

    private String type;
    private String value;
    private BigDecimal hitRate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigDecimal getHitRate() {
        return hitRate;
    }

    public void setHitRate(BigDecimal hitRate) {
        this.hitRate = hitRate;
    }
}
