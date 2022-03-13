package pl.edu.tpawilczus.jee.MonitorCRUDApp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.EmployeeRepository;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.MonitorRepository;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.ProducerRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonitorServiceTest {

    @Autowired
    MonitorRepository monitorRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MonitorService monitorService;

    @BeforeEach
    void setUp() {
        producerRepository.deleteAll();
        employeeRepository.deleteAll();
        monitorRepository.deleteAll();
    }

    @Test
    void addMonitor() {
        Monitor monitor = new Monitor("BigTest", 23, 75);
        monitorService.addMonitor(monitor);
        assertEquals(1, monitorRepository.count());
    }

    @Test
    void findMonitor() {
        Monitor monitor = new Monitor("BigTest", 23, 75);
        monitorRepository.save(monitor);
        assertEquals("BigTest", monitorService.findMonitor(monitor.getId()).getName());
    }

    @Test
    void deleteMonitor() {
        Monitor monitor = new Monitor("BigTest", 23, 75);
        monitorService.addMonitor(monitor);
        monitorService.deleteMonitor(monitor.getId());
        assertEquals(0, monitorRepository.count());
    }
}