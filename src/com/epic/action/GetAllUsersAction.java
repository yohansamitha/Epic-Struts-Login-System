package com.epic.action;

import com.epic.bo.BOFactory;
import com.epic.bo.custom.UserManageBO;
import com.epic.dto.UserDTO;
import com.epic.util.StandardResponse;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllUsersAction extends ActionSupport {
    private StandardResponse standardResponse;

    public StandardResponse getStandardResponse() {
        return standardResponse;
    }

    public void setStandardResponse(StandardResponse standardResponse) {
        this.standardResponse = standardResponse;
    }


    public String getAllUsers() throws Exception {
        UserManageBO userManageBO = BOFactory.getInstance().getBO(BOFactory.BoType.UserManageBOImpl);
        try {
            ArrayList<UserDTO> all = userManageBO.getAll();
            setStandardResponse(new StandardResponse("200", "All Users", all));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "success";
    }
}
