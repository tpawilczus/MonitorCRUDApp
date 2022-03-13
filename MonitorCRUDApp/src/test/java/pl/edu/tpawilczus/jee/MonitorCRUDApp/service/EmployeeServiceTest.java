package pl.edu.tpawilczus.jee.MonitorCRUDApp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Employee;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.EmployeeDTO;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.EmployeeRepository;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.ProducerRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        producerRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void addEmployee() {
        EmployeeDTO employee = new EmployeeDTO("Tadeusz", "Dzwon", 2020);
        employeeService.addEmployee(employee);
        assertEquals(1, employeeRepository.count());
    }

}