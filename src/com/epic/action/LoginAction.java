package com.epic.action;

import com.epic.bo.BOFactory;
import com.epic.bo.custom.UserManageBO;
import com.epic.dto.UserDTO;
import com.epic.util.StandardResponse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware {

    private final UserManageBO userManageBO = BOFactory.getInstance().getBO(BOFactory.BoType.UserManageBOImpl);

    private StandardResponse standardResponse;
    private Map<String, Object> userSession;

    public StandardResponse getStandardResponse() {
        return standardResponse;
    }

    public void setStandardResponse(StandardResponse standardResponse) {
        this.standardResponse = standardResponse;
    }

    public String login() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String userName = request.getHeader("txtUserName");
        String password = request.getHeader("txtPassword");
        System.out.println(userName + " | " + password);

        try {
            if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {
                UserDTO userDTO = userManageBO.validateUser(userName, password);
                if (null != userDTO) {
                    setStandardResponse(new StandardResponse("200", "authentication successful", ""));
                    userSession.put("user", userDTO);
                } else {
                    setStandardResponse(new StandardResponse("403", "username or Password is wrong", ""));
                }
            } else {
                setStandardResponse(new StandardResponse("400", "username or Password is missing", ""));
            }
            return SUCCESS;
        } catch (SQLException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
            return SUCCESS;
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.userSession = map;
    }
}
