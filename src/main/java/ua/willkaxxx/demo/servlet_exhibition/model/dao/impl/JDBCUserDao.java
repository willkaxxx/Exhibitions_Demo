package ua.willkaxxx.demo.servlet_exhibition.model.dao.impl;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.UserDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.mapper.ExhibitionMapper;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.mapper.UserMapper;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import java.sql.*;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private final Logger log = Logger.getLogger(JDBCUserDao.class);
    private final Connection connection;
    public JDBCUserDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(User entity) throws SQLException {
        final String createQuery = "insert into users (email, expired, password, role) values (?,?,?,?);";
        final String joinQuery = "insert ignore into exhibitions_users values (?, ?);";
        final String findId = "SELECT LAST_INSERT_ID();";
        try (PreparedStatement createPreparedStatement = connection.prepareStatement(createQuery);
             PreparedStatement joinPreparedStatement = connection.prepareStatement(joinQuery);
             Statement lastId = connection.createStatement()) {
            connection.setAutoCommit(false);
            createPreparedStatement.setString(1, entity.getEmail());
            createPreparedStatement.setBoolean(2, entity.isExpired());
            createPreparedStatement.setString(3, entity.getPassword());
            createPreparedStatement.setInt(4, entity.getRole().ordinal());
            createPreparedStatement.execute();

            ResultSet tmp = lastId.executeQuery(findId);
            tmp.next();
            entity.setId(tmp.getInt("LAST_INSERT_ID()"));
            joinPreparedStatement.setInt(2, entity.getId());

            for (Exhibition ex : entity.getExhibitions()) {
                joinPreparedStatement.setInt(1, ex.getId());
                joinPreparedStatement.execute();
            }
            connection.commit();
            connection.setAutoCommit(true);
            log.info("Inserted into db: " + entity);
        } catch (SQLException e) {
            log.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Error while rollback");
                throw new RuntimeException();
            }
            throw e;
        }
    }

    @Override
    public Optional<User> findById(int id){
        final String query = "select u.*, e.* from users u " +
                "left join exhibitions_users eu on u.user_id = eu.users_id " +
                "left join exhibitions e on eu.exhibitions_id = e.exhibition_id " +
                "where u.user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            Map<Integer, Exhibition> exhibitionMap = new HashMap<>();
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            UserMapper userMapper = new UserMapper();
            ExhibitionMapper exhibitionMapper = new ExhibitionMapper();
            User user = new User();
            while (rs.next()) {
                if (user.getId() < 1) {
                    user = userMapper.extractFromResultSet(rs);
                }
                Exhibition exhibition = exhibitionMapper.extractFromResultSet(rs);
                exhibitionMapper.makeUnique(exhibitionMap, exhibition);
            }
            user.setExhibitions(new ArrayList<>(exhibitionMap.values()));
            return Optional.of(user);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        final String query = "select u.*, e.* from users u " +
                "left join exhibitions_users eu on u.user_id = eu.users_id " +
                "left join exhibitions e on eu.exhibitions_id = e.exhibition_id;";
        try (Statement statement = connection.createStatement()) {
            Map<Integer, User> userMap = new HashMap<>();
            ResultSet rs = statement.executeQuery(query);
            UserMapper userMapper = new UserMapper();
            ExhibitionMapper exhibitionMapper = new ExhibitionMapper();
            while (rs.next()) {
                User user = userMapper
                        .extractFromResultSet(rs);
                user = userMapper
                        .makeUnique(userMap, user);
                Exhibition exhibition = exhibitionMapper
                        .extractFromResultSet(rs);
                if(!user.getExhibitions().contains(exhibition) && exhibition.getId() > 0){
                    user.getExhibitions().add(exhibition);
                }
            }
            return new ArrayList<>(userMap.values());
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void update(User entity) {
        final String query = "update users set email = ?, expired = ?, password = ?, role = ? where user_id = ?;";
        final String joinTableQuery = "insert ignore into exhibitions_users values (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             PreparedStatement joinTableStatement = connection.prepareStatement(joinTableQuery)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setBoolean(2, entity.isExpired());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setInt(4, entity.getRole().ordinal());
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.execute();

            joinTableStatement.setInt(2, entity.getId());
            for(Exhibition ex : entity.getExhibitions()){
                joinTableStatement.setInt(1, ex.getId());
                joinTableStatement.execute();
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Error while rollback");
                throw new RuntimeException(ex);
            }
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        final String query = "delete from users where user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Error closing db connection");
            throw new RuntimeException(e);
        }
    }
}
