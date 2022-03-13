package pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Employee;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.EmployeeDTO;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.EmployeeService;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/api/employee")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/api/employee")
    public Iterable<EmployeeDTO> allEmployees() {
        return employeeService.allEmployees();
    }

    @GetMapping("api/employee/{id}")
    public EmployeeDTO findEmployee(@PathVariable("id") Long id) {
        return employeeService.findEmployee(id);
    }

    @PutMapping("/api/employee/{id}")
    public EmployeeDTO updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDTO employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("api/employee/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @PostMapping("/api/employee/{id}/monitor/{monitorId}")
    public EmployeeDTO addMonitorToEmployee(@PathVariable("id") Long id, @PathVariable("monitorId") Long monitorId ) {
        return employeeService.addMonitorToEmployee(id, monitorId);
    }

    @DeleteMapping("/api/employee/{id}/monitor/{monitorId}")
    public EmployeeDTO removeMonitorFromEmployee(@PathVariable("id") Long id, @PathVariable("monitorId") Long monitorId) {
        return employeeService.removeMonitorFromEmployee(id, monitorId);
    }
}






