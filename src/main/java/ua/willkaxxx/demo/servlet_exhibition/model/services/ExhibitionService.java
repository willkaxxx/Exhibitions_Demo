package ua.willkaxxx.demo.servlet_exhibition.model.services;

import ua.willkaxxx.demo.servlet_exhibition.model.dao.ExhibitionDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCDaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExhibitionService {
    private final int EXHIBITIONS_PER_PAGE = 3;

    public Optional<Exhibition> getExhibition(int id){
        try(ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()){
            return exhibitionDao.findById(id);
        }
    }

    public Exhibition save(Exhibition exhibition){
        try(ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()){
            if(exhibition.getId() < 1){
                exhibitionDao.create(exhibition);
            }
            else {
                exhibitionDao.update(exhibition);
            }
            return exhibition;
        } catch (SQLException e) {
            e.printStackTrace();
            return exhibition;
        }
    }
    public List<Exhibition> getPage(int page){
        return getPage(page, "1", "asc");
    }

    public List<Exhibition> getPage(int page, String orderBy, String dir){
        try(ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()){
            return exhibitionDao.findAllByPage(page, EXHIBITIONS_PER_PAGE, orderBy, dir);
        }
    }

    public int getTotalPages(){
        try(ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()){
            int numOfRows = exhibitionDao.numberOfRows();
            if(numOfRows % EXHIBITIONS_PER_PAGE > 0){
                return (numOfRows / EXHIBITIONS_PER_PAGE) + 1;
            }
            return  numOfRows / EXHIBITIONS_PER_PAGE;
        }
    }

    public boolean removeHall(Exhibition e, Hall h){
        try(ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()){
            e.getHalls().remove(h);
            return exhibitionDao.deleteHallFromExhibition(e, h);
        }
    }

    public boolean addHall(Exhibition e, Hall h){
        try(ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()){
            e.getHalls().add(h);
            return exhibitionDao.addHallToExhibition(e, h);
        }
    }

    public void delete(int exhibitionId){
        try(ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()){
            exhibitionDao.delete(exhibitionId);
        }
    }
}

