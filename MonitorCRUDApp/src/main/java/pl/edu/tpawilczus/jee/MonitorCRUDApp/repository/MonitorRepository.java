package pl.edu.tpawilczus.jee.MonitorCRUDApp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;

import java.util.List;

@Repository
public interface MonitorRepository extends CrudRepository<Monitor, Long> {

    @Query("select m from Monitor m where m.name = ?1")
    List<Monitor> findByName(String name);

    @Query("select m from Monitor m where m.user.firstName = ?1")
    List<Monitor> findMonitorByUserFirstName(String firstName);
}
