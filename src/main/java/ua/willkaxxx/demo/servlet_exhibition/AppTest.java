package ua.willkaxxx.demo.servlet_exhibition;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.ExhibitionDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.HallDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.UserDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCDaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCUserDao;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Role;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;
import ua.willkaxxx.demo.servlet_exhibition.model.services.UserService;

import java.sql.SQLException;
import java.util.List;

public class AppTest {

    private static final Logger log = Logger.getLogger(AppTest.class);
    public static void main(String[] args) throws SQLException {
//        UserService userService = new UserService();
//        User user = new User();
//        user.setEmail("email");
//        user.setPassword("pawwss");
//        user.setRole(Role.Admin);
//        log.info(userService.createUser(user));

    }
}
