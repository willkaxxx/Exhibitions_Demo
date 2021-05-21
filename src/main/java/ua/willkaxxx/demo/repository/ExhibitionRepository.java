package ua.willkaxxx.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.willkaxxx.demo.entity.Exhibition;

import java.util.Optional;


@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    Page<Exhibition> findAll(Pageable pageable);
    Optional<Exhibition> findById(Long aLong);
}
