package pl.edu.tpawilczus.jee.MonitorCRUDApp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Producer;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.ProducerRepository;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProducerServiceTest {

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    ProducerService producerService;

    @BeforeEach
    void setUp() {
        producerRepository.deleteAll();
    }

    @Test
    void findProducer() {
        Producer producer = new Producer("tak", "ok", 1990);
        producerRepository.save(producer);
        assertEquals("tak", producerService.findProducer(producer.getId()).getName());
    }

    @Test
    void addProducer() {
        Producer producer = new Producer("tak", "ok", 1990);
        producerService.addProducer(producer);
        assertEquals(1, producerRepository.count());
    }

    @Test
    void deleteProducer() {
        Producer producer = new Producer("tak", "ok", 1990);
        producerService.addProducer(producer);
        producerService.deleteProducer(producer.getId());
        assertEquals(0, producerRepository.count());
    }
}