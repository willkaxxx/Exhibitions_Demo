package ua.willkaxxx.demo.servlet_exhibition.model.dao;

import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;

import java.util.List;

public interface HallDao extends GenericDao<Hall> {
    List<Hall> findAllByPage(int page, int perPage);
    int numberOfRows();
}
