package ua.willkaxxx.demo.servlet_exhibition.model.dao.mapper;

import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class HallMapper implements ObjectMapper<Hall> {
    @Override
    public Hall extractFromResultSet(ResultSet rs) throws SQLException {
        if(!rs.isAfterLast()){
            Hall hall = new Hall();
            hall.setId(rs.getInt("hall_id"));
            hall.setAddress(rs.getString("address"));
            return hall;
        }
        return null;
    }

    @Override
    public Hall makeUnique(Map<Integer, Hall> cache, Hall object) {
        cache.putIfAbsent(object.getId(), object);
        return cache.get(object.getId());
    }
}
