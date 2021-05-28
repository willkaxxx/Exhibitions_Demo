package ua.willkaxxx.demo.servlet_exhibition.model.dao.impl;

import ua.willkaxxx.demo.servlet_exhibition.model.dao.DaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.ExhibitionDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.HallDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public ExhibitionDao createExhibitionDao() {
        return new JDBCExhibitionDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public HallDao createHallDao() {
        return new JDBCHallDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
