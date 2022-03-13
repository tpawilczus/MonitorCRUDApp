package pl.edu.tpawilczus.jee.MonitorCRUDApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.api.EmployeeNotFoundException;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Employee;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.EmployeeDTO;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.mapper.EmployeeMapper;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MonitorService monitorService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, MonitorService monitorService, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.monitorService = monitorService;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.to(employeeDTO);
        return employeeMapper.from(employeeRepository.save(employee));
    }

    public Iterable<EmployeeDTO> allEmployees() {
        /*Iterable<Employee> employees =  employeeRepository.findAll();
        List<Monitor> monitors = new ArrayList<>();
        employees.forEach(employee -> employee.getMonitors().toArray(monitors.toArray()));
        Hibernate.initialize(monitors);
        return employees;*/
        Iterable<Employee> employees = employeeRepository.findAll();
        return employeeMapper.from(employees);
    }

    public EmployeeDTO findEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException());
        return employeeMapper.from(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeToUpdate = employeeRepository.findById(id);
        Employee _employee = employeeToUpdate.get();
        _employee.setFirstName(employeeDTO.getFirstName());
        _employee.setLastName(employeeDTO.getLastName());
        _employee.setYoe(employeeDTO.getYoe());
        _employee.setMonitors(employeeDTO.getMonitors());
        return employeeMapper.from(employeeRepository.save(_employee));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO addMonitorToEmployee(Long id, Long monitorId) {
        EmployeeDTO employeeDTO = findEmployee(id);
        Employee employee = employeeMapper.to(employeeDTO);
        Monitor monitor = monitorService.findMonitor(monitorId);
        employee.addMonitor(monitor);
        return employeeMapper.from(employeeRepository.save(employee));
    }

    public EmployeeDTO removeMonitorFromEmployee(Long id, Long monitorId) {
        EmployeeDTO employeeDTO = findEmployee(id);
        Employee employee = employeeMapper.to(employeeDTO);
        Monitor monitor = monitorService.findMonitor(monitorId);
        employee.removeMonitor(monitor);
        return employeeMapper.from(employeeRepository.save(employee));
    }
}



