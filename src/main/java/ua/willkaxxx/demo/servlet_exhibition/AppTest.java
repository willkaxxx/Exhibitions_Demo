package ua.willkaxxx.demo.servlet_exhibition;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.ExhibitionDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.HallDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.UserDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCDaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCUserDao;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class AppTest {

    private static final Logger log = Logger.getLogger(AppTest.class);
    public static void main(String[] args) throws SQLException {

        HallDao hallDao = JDBCDaoFactory.getInstance().createHallDao();
        UserDao userDao = JDBCDaoFactory.getInstance().createUserDao();
        ExhibitionDao exhibitionDao = JDBCDaoFactory.getInstance().createExhibitionDao();
        for(User u : userDao.findAll()){
            log.info(u);
        }
//        hallDao.create(hall);
//        List<Hall> halls = hallDao.findAll();
//        Hall hl = hallDao.findById(2).orElse(null);
//        Hall nn = new Hall();
//        hallDao.create(nn);
//        log.info(nn);
//        for(Hall h : halls){
//            log.info(h);
//        }
//        log.info(halls.size());
    }
}
