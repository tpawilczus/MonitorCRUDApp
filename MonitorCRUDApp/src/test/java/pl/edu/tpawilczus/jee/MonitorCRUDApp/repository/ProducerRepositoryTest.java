package pl.edu.tpawilczus.jee.MonitorCRUDApp.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Producer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProducerRepositoryTest {

    @Autowired
    private ProducerRepository underTest;

    @Test
    void findByCountry() {
        Producer producer1 = new Producer("testproducer", "Poland", 1984);
        Producer producer2 = new Producer("tester", "Germany", 1998);
        underTest.save(producer1);
        underTest.save(producer2);
        Producer testProducer = underTest.findByCountry("Poland").get(0);
        assertEquals(producer1.getName(), testProducer.getName());
    }

    @Test
    void findByCountryOrYof() {
        Producer producer1 = new Producer("testproducer", "Poland", 1984);
        Producer producer2 = new Producer("tester", "Germany", 1998);
        underTest.save(producer1);
        underTest.save(producer2);
        Producer testProducer = underTest.findByCountryOrYof("Germany", 1990).get(0);
        assertEquals(producer2.getCountry(), testProducer.getCountry());
    }
}