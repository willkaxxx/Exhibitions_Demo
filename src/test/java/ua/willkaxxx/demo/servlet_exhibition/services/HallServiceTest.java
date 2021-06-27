package ua.willkaxxx.demo.servlet_exhibition.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.ConnectionPoolHolder;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class HallServiceTest {
    HallService hallService = new HallService();

    @Test
    public void testGetAllHalls_valid() {
        Assertions.assertEquals(3, hallService.getAllHalls().size());
    }

    @Test
    public void testGetHall_valid() {
        Assertions.assertTrue(hallService.getHall(2).isPresent());
    }

    @Test
    public void testGetHall_invalid() {
        Assertions.assertFalse(hallService.getHall(20).isPresent());
    }

    @Test
    public void testGetHallPages() {
        Assertions.assertEquals(1, hallService.getTotalPages());
    }

    @Test
    public void testGetHallPage() {
        Assertions.assertEquals(3, hallService.getPage(1).size());
    }

    @Test
    public void testSaveHall() throws SQLException {
        Hall hall = new Hall.Builder().address("new hall").build();
        try {
            hall = hallService.save(hall);
            Assertions.assertEquals(hall.getAddress(), hallService.getHall(hall.getId()).orElse(new Hall()).getAddress());
            hall.setAddress("editedHallAddress");
            hall = hallService.save(hall);
            Assertions.assertEquals(hall.getAddress(), hallService.getHall(hall.getId()).orElse(new Hall()).getAddress());
        } finally {
            try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
                 Statement statement = connection.createStatement()) {
                String query = "delete from halls where hall_id = " + hall.getId() + " ; ";
                statement.execute(query);
            }
        }
    }
}
