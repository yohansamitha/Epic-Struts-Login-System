package com.epic.action;

import com.epic.bo.BOFactory;
import com.epic.bo.custom.UserManageBO;
import com.epic.util.StandardResponse;

import java.sql.SQLException;

public class DeleteUserAction {
    private String id;
    private StandardResponse standardResponse;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StandardResponse getStandardResponse() {
        return standardResponse;
    }

    public void setStandardResponse(StandardResponse standardResponse) {
        this.standardResponse = standardResponse;
    }


    public String deleteUser() throws Exception {
        UserManageBO userManageBO = BOFactory.getInstance().getBO(BOFactory.BoType.UserManageBOImpl);
        try {
            if (null != id) {
                if (!id.trim().isEmpty()) {
                    if (userManageBO.deleteUser(id)) {
                        setStandardResponse(new StandardResponse("204", "user delete successful", ""));
                    } else {
                        setStandardResponse(new StandardResponse("500", "something went wrong", ""));
                    }
                } else {
                    setStandardResponse(new StandardResponse("400", "No Valid ID found", ""));
                }
            } else {
                setStandardResponse(new StandardResponse("400", "No Valid ID found", ""));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
