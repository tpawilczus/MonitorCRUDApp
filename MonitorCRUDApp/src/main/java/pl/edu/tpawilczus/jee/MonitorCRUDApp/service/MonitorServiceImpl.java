package pl.edu.tpawilczus.jee.MonitorCRUDApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.api.MonitorNotFoundException;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Employee;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Producer;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.User;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.EmployeeRepository;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.MonitorRepository;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.ProducerRepository;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MonitorServiceImpl implements MonitorService {

    private final MonitorRepository monitorRepository;
    private final ProducerRepository producerRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public MonitorServiceImpl(MonitorRepository monitorRepository, ProducerRepository producerRepository, UserRepository userRepository, UserService userService, EmployeeRepository employeeRepository) {
        this.monitorRepository = monitorRepository;
        this.producerRepository = producerRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.employeeRepository = employeeRepository;
    }

    public Monitor addMonitor(Monitor monitor) {
        return monitorRepository.save(monitor);
    }

    public Iterable<Monitor> allMonitors() {
        return monitorRepository.findAll();
    }

    public Monitor findMonitor(Long id) {
        return monitorRepository.findById(id).orElseThrow(() -> new MonitorNotFoundException());
    }

    public Monitor updateMonitor(Long id, Monitor monitor) {
        Optional<Monitor> monitorToUpdate = monitorRepository.findById(id);
        Monitor _monitor = monitorToUpdate.get();
        _monitor.setName(monitor.getName());
        _monitor.setSize(monitor.getSize());
        _monitor.setRefreshRate(monitor.getRefreshRate());
        //_monitor.setUser(monitor.getUser());

        return monitorRepository.save(_monitor);
    }

    public void deleteMonitor(Long id) {
        monitorRepository.deleteById(id);
    }

    public Monitor setUserToMonitor(Long id, Long userId) {
        Monitor monitor = findMonitor(id);
        User user = userService.findUser(userId);
        monitor.setUser(user);
        user.setMonitor(monitor);
        return monitorRepository.save(monitor);
    }

    public Monitor removeUserFromMonitor(Long id) {
        Monitor monitor = findMonitor(id);
        User user = monitor.getUser();
        monitor.setUser(null);
        user.setMonitor(null);
        return monitorRepository.save(monitor);
    }

    public void enterSomeData() {
        Monitor monitor1 = new Monitor("EX2510", 23, 75);
        Monitor monitor2 = new Monitor("EX2710", 23, 60);
        Monitor monitor3 = new Monitor("EX3203", 50, 60);
        Monitor monitor4 = new Monitor("BigOne", 60, 70);
        List<Monitor> monitors = new ArrayList<>(Arrays.asList(monitor1, monitor2, monitor3, monitor4));
        monitorRepository.saveAll(monitors);

        List<Monitor> monitorList1 = new ArrayList<>(Arrays.asList(monitor1, monitor2));

        Producer producer1 = new Producer("BENQ", "Taiwan", 1984);
        Producer producer2 = new Producer("Samsung", "South Korea", 1938);
        Producer producer3 = new Producer("Apple", "USA", 1976);
        Producer producer4 = new Producer("ASUS", "Taiwan", 1989);
        List<Producer> producers = new ArrayList<>(Arrays.asList(producer1, producer2, producer3, producer4));
        producerRepository.saveAll(producers);

        producer1.setMonitors(monitorList1);
        producer2.setMonitors(List.of(monitor3));
        producer4.setMonitors(List.of(monitor4));


        User user1 = new User("Adam", "Nowak", 1950);
        User user2 = new User("Kamil", "Kowalski", 1980);
        User user3 = new User("Janek", "Wazny", 1997);
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3));
        userRepository.saveAll(users);

        user1.setMonitor(monitor2);
        user2.setMonitor(monitor3);
        user3.setMonitor(monitor1);

        Employee employee1 = new Employee("Tadeusz", "Dzwon", 2020);
        Employee employee2 = new Employee("Tomasz", "Niedzwon", 2021);
        Employee employee3 = new Employee("Adam", "Nawal", 2021);
        List<Employee> employees = new ArrayList<>(Arrays.asList(employee1, employee2, employee3));
        employeeRepository.saveAll(employees);

        employee1.setMonitors(monitors);

        monitorRepository.findByName("BigOne").stream().forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////1");
        monitorRepository.findMonitorByUserFirstName("Kamil").stream().forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////2");
        producerRepository.findByCountry("Taiwan").stream().forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////3");
        producerRepository.findByCountryOrYof("USA", 1984).stream().forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////4");
        employeeRepository.findByFirstName("Tomasz").stream().forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////5");
        employeeRepository.findAll(Sort.by("firstName")).stream().forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////6");
        employeeRepository.findEmployeesByMonitorsNameStartingWith("B").stream().forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////7");
        userRepository.findByMonitorNameIsEndingWithOrderByYob("0").stream().forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////8");
    }
}
