package com.epic.action;

import com.epic.bo.BOFactory;
import com.epic.bo.custom.UserManageBO;
import com.epic.dto.UserDTO;
import com.epic.util.StandardResponse;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.sql.SQLException;

public class CreateUserAction extends ActionSupport implements ModelDriven {

    private UserDTO userDTO = new UserDTO();
    private StandardResponse standardResponse;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public StandardResponse getStandardResponse() {
        return standardResponse;
    }

    public void setStandardResponse(StandardResponse standardResponse) {
        this.standardResponse = standardResponse;
    }

    public String createUser() throws Exception {
        UserManageBO userManageBO = BOFactory.getInstance().getBO(BOFactory.BoType.UserManageBOImpl);
        try {
            String isValidUser = validateUserData(userDTO);
            if (isValidUser.equals("true")) {
                if (userManageBO.saveUser(userDTO))
                    setStandardResponse(new StandardResponse("201", "user save successful", userDTO));
                else {
                    setStandardResponse(new StandardResponse("500", "something went wrong", userDTO));
                }
            } else {
                setStandardResponse(new StandardResponse("400", isValidUser, userDTO));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    private String validateUserData(UserDTO userDTO) {
        if (userDTO == null) {
            return "No User Data Found";
        } else if (userDTO.getName().trim().isEmpty()) {
            return "No User Name Found";
        } else if (userDTO.getAddress().trim().isEmpty()) {
            return "No User Address Found";
        } else if (userDTO.getContact().trim().isEmpty()) {
            return "No User Contact Found";
        } else if (userDTO.getEmailAddress().trim().isEmpty()) {
            return "No User Email Address Found";
        } else if (userDTO.getPassword().trim().isEmpty()) {
            return "No User Password Found";
        } else {
            return "true";
        }
    }

    @Override
    public Object getModel() {
        return userDTO;
    }
}
