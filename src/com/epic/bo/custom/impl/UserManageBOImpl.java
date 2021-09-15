package com.epic.bo.custom.impl;

import com.epic.bo.custom.UserManageBO;
import com.epic.dao.DAOFactory;
import com.epic.dao.custom.UserManageDAO;
import com.epic.dto.UserDTO;
import com.epic.model.User;
import com.epic.security.AES;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserManageBOImpl implements UserManageBO {

    private final UserManageDAO userManageDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.UserManageDAOImpl);

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        userDTO.setPassword(AES.getInstance().encrypt(userDTO.getPassword()));
        return userManageDAO.add(new User(userDTO.getName(), userDTO.getAddress(), userDTO.getContact(), userDTO.getEmailAddress(),
                userDTO.getPassword()));
    }

    @Override
    public UserDTO validateUser(String userName, String password) throws Exception {
        User user = userManageDAO.validateUser(userName, AES.getInstance().encrypt(password));
        return (user != null) ? new UserDTO(user.getId(), user.getName(), user.getAddress(), user.getContact(), user.getEmailAddress(),
                user.getPassword()) : null;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userManageDAO.update(new User(userDTO.getId(), userDTO.getName(), userDTO.getAddress(), userDTO.getContact(),
                userDTO.getEmailAddress(), userDTO.getPassword()));
    }

    @Override
    public ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<User> all = userManageDAO.getAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User userDTO : all) {
            userDTOS.add(new UserDTO(userDTO.getId(), userDTO.getName(), userDTO.getAddress(), userDTO.getContact(),
                    userDTO.getEmailAddress(), userDTO.getPassword()));
        }
        return userDTOS;
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userManageDAO.delete(id);
    }
}
