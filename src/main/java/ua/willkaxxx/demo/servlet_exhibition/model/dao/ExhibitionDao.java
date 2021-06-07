package ua.willkaxxx.demo.servlet_exhibition.model.dao;

import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.OrderDir;

import java.util.List;
import java.util.Optional;

public interface ExhibitionDao extends GenericDao<Exhibition> {
    List<Exhibition> findAllByPage(int page, int perPage);
    List<Exhibition> findAllByPageFiltered(int page, int perPage, String orderBy, OrderDir dir,
                                           Optional<String> begStart, Optional<String> begStop,Optional<String> endStart,Optional<String> endStop);
    int numberOfRows();
    int numberOfRowsFiltered(Optional<String> begStart, Optional<String> begStop,Optional<String> endStart,Optional<String> endStop);
    boolean deleteHallFromExhibition(Exhibition exhibition, Hall hall);
    boolean addHallToExhibition(Exhibition exhibition, Hall hall);
}
