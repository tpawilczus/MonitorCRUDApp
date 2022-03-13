package pl.edu.tpawilczus.jee.MonitorCRUDApp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Producer;

import java.util.List;

@Repository
public interface ProducerRepository extends CrudRepository<Producer, Long> {

    @Query("select p from Producer p where p.country = ?1")
    List<Producer> findByCountry(String country);

    @Query("select p from Producer p where p.country=?1 or p.yof=?2")
    List<Producer> findByCountryOrYof(String country, int yof);
}
