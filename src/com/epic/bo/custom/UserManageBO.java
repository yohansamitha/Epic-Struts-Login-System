package com.epic.bo.custom;

import com.epic.bo.SuperBO;
import com.epic.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserManageBO extends SuperBO {
    boolean saveUser(UserDTO userDTO) throws Exception;

    UserDTO validateUser(String userName, String password) throws Exception;

    boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
}
