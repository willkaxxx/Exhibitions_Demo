package ua.willkaxxx.demo.servlet_exhibition.model.services;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.ConfigReader;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.HallDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCDaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HallService {
    private final Logger log = Logger.getLogger(HallService.class);
    private final int HALLS_PER_PAGE = Integer.parseInt(ConfigReader.getProperties().getProperty("page.size"));

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
        } catch (SQLException ex) {
            log.error("Sql exception, exception code: " + ex.getSQLState());
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
