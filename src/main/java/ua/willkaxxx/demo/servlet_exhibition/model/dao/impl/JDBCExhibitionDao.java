package ua.willkaxxx.demo.servlet_exhibition.model.dao.impl;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.ExhibitionDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.mapper.ExhibitionMapper;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.mapper.HallMapper;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.mapper.UserMapper;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import java.sql.*;
import java.util.*;

public class JDBCExhibitionDao implements ExhibitionDao {
    private final Logger log = Logger.getLogger(JDBCExhibitionDao.class);
    private final Connection connection;
    public JDBCExhibitionDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(Exhibition entity) {
        final String createQuery = "insert into exhibitions (beginning, canseled, cost, end, exhibition_name, subject) values (?,?,?,?,?,?);";
        final String joinHallQuery = "insert ignore into exhibitions_users values (?, ?);";
        final String joinUserQuery = "insert ignore into exhibitions_users values (?, ?);";
        final String findId = "SELECT LAST_INSERT_ID();";
        try (PreparedStatement createPreparedStatement = connection.prepareStatement(createQuery);
             PreparedStatement joinHallPreparedStatement = connection.prepareStatement(joinHallQuery);
             PreparedStatement joinUserPreparedStatement = connection.prepareStatement(joinUserQuery);
             Statement lastId = connection.createStatement()) {
            connection.setAutoCommit(false);
            createPreparedStatement.setTimestamp(1, entity.getBeginning());
            createPreparedStatement.setBoolean(2, entity.isCanceled());
            createPreparedStatement.setBigDecimal(3, entity.getCost());
            createPreparedStatement.setTimestamp(4, entity.getEnd());
            createPreparedStatement.setString(5, entity.getName());
            createPreparedStatement.setString(6, entity.getSubject());
            createPreparedStatement.execute();

            ResultSet tmp = lastId.executeQuery(findId);
            tmp.next();
            entity.setId(tmp.getInt("LAST_INSERT_ID()"));
            joinHallPreparedStatement.setInt(1, entity.getId());
            joinUserPreparedStatement.setInt(1, entity.getId());

            for (Hall hall : entity.getHalls()) {
                joinHallPreparedStatement.setInt(1, hall.getId());
                joinHallPreparedStatement.execute();
            }

            for (User user : entity.getUsers()) {
                joinUserPreparedStatement.setInt(1, user.getId());
                joinUserPreparedStatement.execute();
            }
            connection.commit();
            connection.setAutoCommit(true);
            log.info("Inserted into db: " + entity);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Error while rollback");
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Exhibition> findById(int id) {
        final String query = "select e.*, h.*, u.* from exhibitions e " +
                "left join exhibitions_halls eh on e.exhibition_id = eh.exhibitions_id " +
                "left join halls h on eh.halls_id = h.hall_id " +
                "left join exhibitions_users eu on e.exhibition_id = eu.exhibitions_id " +
                "left join users u on eu.users_id = u.user_id " +
                "where e.exhibition_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            Map<Integer, Hall> hallMap = new HashMap<>();
            Map<Integer, User> userMap = new HashMap<>();
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            HallMapper hallMapper = new HallMapper();
            UserMapper userMapper = new UserMapper();
            ExhibitionMapper exhibitionMapper = new ExhibitionMapper();
            Exhibition exhibition = new Exhibition();
            while (rs.next()) {
                if (exhibition.getId() < 1) {
                    exhibition = exhibitionMapper.extractFromResultSet(rs);
                }
                User user = userMapper.extractFromResultSet(rs);
                Hall hall = hallMapper.extractFromResultSet(rs);
                userMapper.makeUnique(userMap, user);
                hallMapper.makeUnique(hallMap, hall);
            }
            exhibition.setUsers(new ArrayList<>(userMap.values()));
            exhibition.setHalls(new ArrayList<>(hallMap.values()));
            return Optional.of(exhibition);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Exhibition> findAll() {
        final String query = "select e.*, h.*, u.* from exhibitions e " +
                "left join exhibitions_halls eh on e.exhibition_id = eh.exhibitions_id " +
                "left join halls h on eh.halls_id = h.hall_id " +
                "left join exhibitions_users eu on e.exhibition_id = eu.exhibitions_id " +
                "left join users u on eu.users_id = u.user_id;";
        try (Statement statement = connection.createStatement()) {
            Map<Integer, Exhibition> exhibitionMap = new HashMap<>();
            ResultSet rs = statement.executeQuery(query);
            HallMapper hallMapper = new HallMapper();
            UserMapper userMapper = new UserMapper();
            ExhibitionMapper exhibitionMapper = new ExhibitionMapper();
            while (rs.next()) {
                Hall hall = hallMapper
                        .extractFromResultSet(rs);
                Exhibition exhibition = exhibitionMapper
                        .extractFromResultSet(rs);
                User user = userMapper
                        .extractFromResultSet(rs);
                exhibition = exhibitionMapper.makeUnique(exhibitionMap, exhibition);
                if(!exhibition.getUsers().contains(user) && user.getId() > 0){
                    exhibition.getUsers().add(user);
                }
                if(!exhibition.getHalls().contains(hall) && hall.getId() > 0){
                    exhibition.getHalls().add(hall);
                }
            }
            return new ArrayList<>(exhibitionMap.values());
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Exhibition entity) {
        final String query = "update exhibitions set beginning = ? , canseled = ?, cost = ?, end = ?, exhibition_name = ?, subject = ?;";
        final String joinHallQuery = "insert ignore into exhibitions_halls values (?, ?);";
        final String joinUserQuery = "insert ignore into exhibitions_users values (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             PreparedStatement joinHallStatement = connection.prepareStatement(joinHallQuery);
        PreparedStatement joinUserStatement = connection.prepareStatement(joinUserQuery)) {
            connection.setAutoCommit(false);
            preparedStatement.setTimestamp(1, entity.getBeginning());
            preparedStatement.setBoolean(2, entity.isCanceled());
            preparedStatement.setBigDecimal(2, entity.getCost());
            preparedStatement.setTimestamp(2, entity.getEnd());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(2, entity.getSubject());
            preparedStatement.execute();

            joinHallStatement.setInt(1, entity.getId());
            joinUserStatement.setInt(1, entity.getId());
            for(Hall ex : entity.getHalls()){
                joinHallStatement.setInt(2, ex.getId());
                joinHallStatement.execute();
            }
            for(User ex : entity.getUsers()){
                joinUserStatement.setInt(2, ex.getId());
                joinUserStatement.execute();
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        final String query = "delete from exhibitions where exhibition_id = ?;";
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
            throw new RuntimeException(e);
        }
    }
}
