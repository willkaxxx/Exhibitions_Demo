package ua.willkaxxx.demo.servlet_exhibition.model.dao.mapper;

import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class HallMapper implements ObjectMapper<Hall> {
    @Override
    public Hall extractFromResultSet(ResultSet rs) throws SQLException {
        if (!rs.isAfterLast()) {
            return new Hall.Builder().
                    id(rs.getInt("hall_id")).
                    address(rs.getString("address")).build();
        }
        return null;
    }

    @Override
    public Hall makeUnique(Map<Integer, Hall> cache, Hall object) {
        if (object.getId() > 0)
            cache.putIfAbsent(object.getId(), object);
        return cache.get(object.getId());
    }
}
