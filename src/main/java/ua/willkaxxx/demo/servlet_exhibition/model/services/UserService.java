package ua.willkaxxx.demo.servlet_exhibition.model.services;

import ua.willkaxxx.demo.servlet_exhibition.model.dao.UserDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCDaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    public int createUser(User user) throws SQLException {
        try(UserDao userDao = JDBCDaoFactory.getInstance().createUserDao()){
            userDao.create(user);
        }
        return user.getId();
    }

    public Optional<User> findUser(String email){
        try(UserDao userDao = JDBCDaoFactory.getInstance().createUserDao()){
            return userDao.findByEmail(email);
        }
    }

    public boolean enroll(Exhibition exhibition, User user){
        try(UserDao userDao = JDBCDaoFactory.getInstance().createUserDao()){
            return userDao.enroll(exhibition, user);
        }
    }

    public List<Exhibition> getExhibitions(User user){
        try(UserDao userDao = JDBCDaoFactory.getInstance().createUserDao()){
            return userDao.findExhibitionsByUser(user);
        }
    }
}
