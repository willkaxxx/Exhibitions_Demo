package ua.willkaxxx.demo.servlet_exhibition.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.UserDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.ConnectionPoolHolder;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCDaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;
import ua.willkaxxx.demo.servlet_exhibition.model.services.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserServiceTest {
    UserService userService = new UserService();
    private final String VALID_EMAIL = "admin@admin";
    private final String INVALID_EMAIL = "a@admin";

    @Test
    public void testFindUser_valid() {
        Assertions.assertTrue(userService.findUser(VALID_EMAIL).isPresent());
    }

    @Test
    public void testFindUser_invalid() {
        Assertions.assertFalse(userService.findUser(INVALID_EMAIL).isPresent());
    }

    @Test
    public void testCreateUser_invalid() {
        int id = -1;
        try {
            id = userService.createUser(new User.Builder().email(VALID_EMAIL).build());
        } catch (SQLException e) {
            Assertions.assertEquals("23000", e.getSQLState());
        } finally {
            JDBCDaoFactory.getInstance().createUserDao().delete(id);
        }
    }

    @Test
    public void testCreateUser_valid() {
        int id = -1;
        try {
            id = userService.createUser(new User.Builder().email(INVALID_EMAIL).password("12345678").build());
        } catch (SQLException e) {
            Assertions.fail("Sql exception while inserting valid user");
        } finally {
            try (UserDao userDao = JDBCDaoFactory.getInstance().createUserDao()) {
                userDao.delete(id);
            }
        }
    }

    @Test
    public void testGetExhibitions() {
        Assertions.assertEquals(2, userService.getExhibitions(new User.Builder().email(VALID_EMAIL).id(1).build()).size());
    }

    @Test
    public void testEnroll() throws SQLException {
        try{
            Exhibition exhibition = new Exhibition.Builder().id(3).build();
            User user = new User.Builder().id(1).build();
            userService.enroll(exhibition, user);
            Assertions.assertTrue(userService.getExhibitions(user).contains(exhibition));
        }
        finally {
            try(Connection connection = ConnectionPoolHolder.getDataSource().getConnection()){
                try (Statement statement = connection.createStatement()) {
                    String query = "delete from exhibitions_users where exhibitions_id = 3 and users_id = 1 ; ";
                    statement.execute(query);
                }
            }
        }
    }
}
