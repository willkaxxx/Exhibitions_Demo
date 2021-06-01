package ua.willkaxxx.demo.servlet_exhibition.model.services;

import ua.willkaxxx.demo.servlet_exhibition.model.dao.HallDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCDaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;


import java.util.List;
import java.util.Optional;

public class HallService {
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

    public boolean save(Hall hall){
        try(HallDao hallDao = JDBCDaoFactory.getInstance().createHallDao()){
            hallDao.update(hall);
            return true;
        }
    }
}
