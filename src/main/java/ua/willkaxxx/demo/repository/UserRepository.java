package ua.willkaxxx.demo.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.willkaxxx.demo.entity.Role;
import ua.willkaxxx.demo.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@NonNull String email);
    List<User> findAllByRole(Role role);
//    @Query("select u\n" +
//            "from User as u\n" +
//            "inner join  MastersServices as sm\n" +
//            "\ton u.id = sm.userId\n" +
//            "inner join Service as s\n" +
//            "\ton sm.serviceId = s.id\n" +
//            "where s.id = ?1")
//    List<User> getMastersByServiceId(Long id);
}
