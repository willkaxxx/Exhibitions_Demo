package ua.willkaxxx.demo.servlet_exhibition.model.dao;

import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    public Optional<User> findByEmail(String email);
}
