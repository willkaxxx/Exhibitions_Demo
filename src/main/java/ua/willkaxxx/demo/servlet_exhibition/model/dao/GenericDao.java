package ua.willkaxxx.demo.servlet_exhibition.model.dao;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    void create (T entity) throws SQLException;
    Optional<T> findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
