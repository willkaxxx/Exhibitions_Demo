package ua.willkaxxx.demo.servlet_exhibition.model.dao.mapper;

import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExhibitionMapper implements ObjectMapper<Exhibition> {
    @Override
    public Exhibition extractFromResultSet(ResultSet rs) throws SQLException {
        Exhibition exhibition = new Exhibition();
        exhibition.setId(rs.getInt("exhibition_id"));
        exhibition.setBeginning(rs.getTimestamp("beginning"));
        exhibition.setEnd(rs.getTimestamp("end"));
        exhibition.setCanceled(rs.getBoolean("canceled"));
        exhibition.setCost(rs.getBigDecimal("cost"));
        exhibition.setSubject(rs.getString("subject"));
        exhibition.setName(rs.getString("exhibition_name"));
        return exhibition;
    }

    @Override
    public Exhibition makeUnique(Map<Integer, Exhibition> cache, Exhibition object) {
        if (object.getId() > 0)
            cache.putIfAbsent(object.getId(), object);
        return cache.get(object.getId());
    }
}
