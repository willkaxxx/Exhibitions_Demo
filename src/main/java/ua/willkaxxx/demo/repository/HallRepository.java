package ua.willkaxxx.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.willkaxxx.demo.entity.Exhibition;
import ua.willkaxxx.demo.entity.Hall;


import java.util.List;
import java.util.Optional;


@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    Page<Hall> findAll(Pageable pageable);
    Page<Hall> findAllByExhibitions(Exhibition exhibition, Pageable pageable);
    List<Hall> findAllByExhibitions(Exhibition exhibition);
    Optional<Hall> findById(Long id);
}