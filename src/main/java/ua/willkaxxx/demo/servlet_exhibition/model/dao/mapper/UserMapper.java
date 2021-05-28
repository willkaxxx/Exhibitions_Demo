package ua.willkaxxx.demo.servlet_exhibition.model.dao.mapper;

import ua.willkaxxx.demo.servlet_exhibition.model.entity.Role;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User>{
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setExpired(rs.getBoolean("expired"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.values()[rs.getInt("role")]);
        return user;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User object) {
        cache.putIfAbsent(object.getId(), object);
        return cache.get(object.getId());
    }
}
