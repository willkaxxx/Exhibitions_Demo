package ua.willkaxxx.demo.servlet_exhibition.model.dao.impl;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.HallDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.mapper.ExhibitionMapper;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.mapper.HallMapper;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;

import java.sql.*;
import java.util.*;

public class JDBCHallDao implements HallDao {
    private final Logger log = Logger.getLogger(JDBCHallDao.class);
    private final Connection connection;

    public JDBCHallDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Hall entity) {
        final String hallQuery = "insert into halls (address) values (?);";
        final String exhibitionsQuery = "insert ignore into exhibitions_halls values (?, ?);";
        final String findId = "SELECT LAST_INSERT_ID();";
        try (PreparedStatement hallPreparedStatement = connection.prepareStatement(hallQuery);
             PreparedStatement exhibitionsPreparedStatement = connection.prepareStatement(exhibitionsQuery);
             Statement lastId = connection.createStatement()) {
            connection.setAutoCommit(false);
            hallPreparedStatement.setString(1, entity.getAddress());
            hallPreparedStatement.execute();

            ResultSet tmp = lastId.executeQuery(findId);
            tmp.next();
            entity.setId(tmp.getInt("LAST_INSERT_ID()"));
            exhibitionsPreparedStatement.setInt(1, entity.getId());

            for (Exhibition ex : entity.getExhibitions()) {
                exhibitionsPreparedStatement.setInt(2, ex.getId());
                exhibitionsPreparedStatement.execute();
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
    public Optional<Hall> findById(int id){
        final String query = "select e.*, h.* from halls e " +
                "left join exhibitions_halls eh on e.hall_id = eh.halls_id " +
                "left join exhibitions h on eh.exhibitions_id = h.exhibition_id " +
                "where e.hall_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            Map<Integer, Exhibition> exhibitionMap = new HashMap<>();
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            HallMapper hallMapper = new HallMapper();
            ExhibitionMapper exhibitionMapper = new ExhibitionMapper();
            Hall hall = new Hall();
            while (rs.next()) {
                if (hall.getId() < 1) {
                    hall = hallMapper.extractFromResultSet(rs);
                }
                Exhibition exhibition = exhibitionMapper.extractFromResultSet(rs);
                exhibitionMapper.makeUnique(exhibitionMap, exhibition);
            }
            hall.setExhibitions(new ArrayList<>(exhibitionMap.values()));
            return Optional.of(hall);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Hall> findAll() {
        final String query = "select e.*, h.* from halls e " +
                "left join exhibitions_halls eh on e.hall_id = eh.halls_id " +
                "left join exhibitions h on eh.exhibitions_id = h.exhibition_id;";
        try (Statement statement = connection.createStatement()) {
            Map<Integer, Hall> hallMap = new HashMap<>();
            Map<Integer, Exhibition> exhibitionMap = new HashMap<>();
            ResultSet rs = statement.executeQuery(query);
            HallMapper hallMapper = new HallMapper();
            ExhibitionMapper exhibitionMapper = new ExhibitionMapper();
            while (rs.next()) {
                Hall hall = hallMapper
                        .extractFromResultSet(rs);
                Exhibition exhibition = exhibitionMapper
                        .extractFromResultSet(rs);
                hall = hallMapper
                        .makeUnique(hallMap, hall);
                exhibition = exhibitionMapper
                        .makeUnique(exhibitionMap, exhibition);
                hall.getExhibitions().add(exhibition);
            }
            return new ArrayList<>(hallMap.values());
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Hall entity) {
        final String query = "update halls set address = ? where hall_id = ?;";
        final String joinTableQuery = "insert ignore into exhibitions_halls values (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
            PreparedStatement joinTableStatement = connection.prepareStatement(joinTableQuery)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, entity.getAddress());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.execute();

            joinTableStatement.setInt(2, entity.getId());
            for(Exhibition ex : entity.getExhibitions()){
                joinTableStatement.setInt(1, ex.getId());
                joinTableStatement.execute();
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
        final String query = "delete from halls where hall_id = ?;\n";
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
