package com.sap.integration.vo.requestVo;

import com.sap.integration.model.CommandPara;

import java.util.List;

public class Command {

    private String target;
    private String action;
    private List<CommandPara> commandParaList;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<CommandPara> getCommandParaList() {
        return commandParaList;
    }

    public void setCommandParaList(List<CommandPara> commandParaList) {
        this.commandParaList = commandParaList;
    }
}
