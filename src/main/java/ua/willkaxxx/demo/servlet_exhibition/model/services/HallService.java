package ua.willkaxxx.demo.servlet_exhibition.model.services;

import ua.willkaxxx.demo.servlet_exhibition.model.dao.HallDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCDaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HallService {
    private final int HALLS_PER_PAGE = 3;

    public List<Hall> getAllHalls(){
        try(HallDao hallDao = JDBCDaoFactory.getInstance().createHallDao()){
            return hallDao.findAll();
        }
    }

    public Optional<Hall> getHall(int id){
        try(HallDao hallDao = JDBCDaoFactory.getInstance().createHallDao()){
            return hallDao.findById(id);
        }
    }

    public Hall save(Hall hall){
        try(HallDao hallDao = JDBCDaoFactory.getInstance().createHallDao()){
            if(hall.getId() < 1){
                hallDao.create(hall);
            }
            else {
                hallDao.update(hall);
            }
            return hall;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return hall;
        }
    }
    public List<Hall> getPage(int page){
        try(HallDao hallDao = JDBCDaoFactory.getInstance().createHallDao()){
            return hallDao.findAllByPage(page, HALLS_PER_PAGE);
        }
    }

    public int getTotalPages(){
        try(HallDao hallDao = JDBCDaoFactory.getInstance().createHallDao()){
            int numOfRows = hallDao.numberOfRows();
            if(numOfRows % HALLS_PER_PAGE > 0){
                return (numOfRows / HALLS_PER_PAGE) + 1;
            }
            return  numOfRows / HALLS_PER_PAGE;
        }
    }
}