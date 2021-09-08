package com.epic.action;

import com.epic.dto.UserDTO;
import com.epic.util.StandardResponse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class LogoutAction extends ActionSupport implements SessionAware {
    private StandardResponse standardResponse;
    private Map<String, Object> userSession;

    public StandardResponse getStandardResponse() {
        return standardResponse;
    }

    public void setStandardResponse(StandardResponse standardResponse) {
        this.standardResponse = standardResponse;
    }


    public String logout() {
        UserDTO user = (UserDTO) this.userSession.remove("user");
        setStandardResponse(new StandardResponse("200", "username logout successful", user));
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.userSession = session;
    }
}
