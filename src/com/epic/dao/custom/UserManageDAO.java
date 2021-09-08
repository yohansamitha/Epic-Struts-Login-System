package com.epic.dao.custom;

import com.epic.dao.CrudDAO;
import com.epic.model.User;

import java.sql.SQLException;

public interface UserManageDAO extends CrudDAO<User, String> {
    User validateUser(String userName, String password) throws SQLException, ClassNotFoundException;
}
