package ua.willkaxxx.demo.servlet_exhibition.model.dao;

import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.OrderDir;

import java.util.List;

public interface ExhibitionDao extends GenericDao<Exhibition> {
    List<Exhibition> findAllByPage(int page, int perPage, String orderBy, OrderDir dir);
    int numberOfRows();
    boolean deleteHallFromExhibition(Exhibition exhibition, Hall hall);
    boolean addHallToExhibition(Exhibition exhibition, Hall hall);
}
