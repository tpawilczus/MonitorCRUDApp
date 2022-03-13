package pl.edu.tpawilczus.jee.MonitorCRUDApp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u where u.monitor.name like concat('%', ?1) order by u.yob")
    List<User> findByMonitorNameIsEndingWithOrderByYob(String ending);
}
