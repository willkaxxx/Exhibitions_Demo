package ua.willkaxxx.demo.servlet_exhibition.model.services;

import ua.willkaxxx.demo.servlet_exhibition.model.dao.UserDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCDaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import java.sql.SQLException;

public class UserService {
    public int createUser(User user) throws SQLException {
        try(UserDao userDao = JDBCDaoFactory.getInstance().createUserDao()){
            userDao.create(user);
        }
        return user.getId();
    }
}
