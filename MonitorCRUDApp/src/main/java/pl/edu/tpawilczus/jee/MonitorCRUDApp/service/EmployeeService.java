package pl.edu.tpawilczus.jee.MonitorCRUDApp.service;

import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Employee;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.EmployeeDTO;

public interface EmployeeService {
    Iterable<EmployeeDTO> allEmployees();
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO findEmployee(Long id);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
    EmployeeDTO addMonitorToEmployee(Long id, Long monitorId);
    EmployeeDTO removeMonitorFromEmployee(Long id, Long monitorId);
}
