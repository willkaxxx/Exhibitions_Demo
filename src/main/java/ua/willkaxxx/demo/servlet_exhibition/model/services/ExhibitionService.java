package ua.willkaxxx.demo.servlet_exhibition.model.services;

import ua.willkaxxx.demo.servlet_exhibition.model.ConfigReader;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.ExhibitionDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCDaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.OrderDir;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExhibitionService {
    private final int EXHIBITIONS_PER_PAGE = Integer.parseInt(ConfigReader.getInstance().getProperty("page.size"));

    public Optional<Exhibition> getExhibition(int id) {
        try (ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()) {
            return exhibitionDao.findById(id);
        }
    }

    public Exhibition save(Exhibition exhibition) {
        try (ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()) {
            if (exhibition.getId() < 1) {
                exhibitionDao.create(exhibition);
            } else {
                exhibitionDao.update(exhibition);
            }
            return exhibition;
        } catch (SQLException e) {
            e.printStackTrace();
            return exhibition;
        }
    }

    public List<Exhibition> getPage(int page) {
        try (ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()) {
            return exhibitionDao.findAllByPage(page, EXHIBITIONS_PER_PAGE);
        }
    }

    public List<Exhibition> getPage(int page, String orderBy, OrderDir dir,
                                    Optional<String> begStart, Optional<String> begStop, Optional<String> endStart, Optional<String> endStop) {
        try (ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()) {
            return exhibitionDao.findAllByPageFiltered(page, EXHIBITIONS_PER_PAGE, orderBy, dir, begStart, begStop, endStart, endStop);
        }
    }

    public int getTotalPagesFiltered(Optional<String> begStart, Optional<String> begStop, Optional<String> endStart, Optional<String> endStop) {
        try (ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()) {
            int numOfRows = exhibitionDao.numberOfRowsFiltered(begStart, begStop,  endStart, endStop);
            if (numOfRows % EXHIBITIONS_PER_PAGE > 0) {
                return (numOfRows / EXHIBITIONS_PER_PAGE) + 1;
            }
            return numOfRows / EXHIBITIONS_PER_PAGE;
        }
    }
    public int getTotalPages(){
        try (ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()) {
            int numOfRows = exhibitionDao.numberOfRows();
            if (numOfRows % EXHIBITIONS_PER_PAGE > 0) {
                return (numOfRows / EXHIBITIONS_PER_PAGE) + 1;
            }
            return numOfRows / EXHIBITIONS_PER_PAGE;
        }
    }

    public boolean removeHall(int exhibitionId, int hallId) {
        try (ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()) {
            exhibitionDao.deleteHallFromExhibition(exhibitionId, hallId);
            return true;
        }
    }

    public boolean addHall(int exhibitionId, int hallId) {
        try (ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()) {
            return exhibitionDao.addHallToExhibition(exhibitionId, hallId);
        }
    }

    public void delete(int exhibitionId) {
        try (ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao()) {
            exhibitionDao.delete(exhibitionId);
        }
    }
}

