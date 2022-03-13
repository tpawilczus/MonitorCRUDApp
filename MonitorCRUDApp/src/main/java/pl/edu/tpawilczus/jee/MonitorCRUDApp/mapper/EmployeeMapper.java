package pl.edu.tpawilczus.jee.MonitorCRUDApp.mapper;

import org.mapstruct.Mapper;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Employee;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.EmployeeDTO;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Iterable<EmployeeDTO> from(Iterable<Employee> employee);
    EmployeeDTO from(Employee employee);
    Employee to(EmployeeDTO employeeDTO);
}
