package pl.edu.tpawilczus.jee.MonitorCRUDApp.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select e from Employee e where e.firstName = ?1")
    List<Employee> findByFirstName(String firstName);

    @Query("select e from Employee e")
    List<Employee> findAll(Sort sort);

    @Query("select e from Employee e left join e.monitors monitors where monitors.name like concat(?1, '%')")
    List<Employee> findEmployeesByMonitorsNameStartingWith(String name);

}
