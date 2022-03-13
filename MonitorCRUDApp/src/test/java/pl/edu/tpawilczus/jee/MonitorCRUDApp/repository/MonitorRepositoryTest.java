package pl.edu.tpawilczus.jee.MonitorCRUDApp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonitorRepositoryTest {

    @Autowired
    private MonitorRepository underTest;

    @Test
    void findByName() {
        Monitor monitor = new Monitor("testing", 60, 70);
        List<Monitor> testList = new ArrayList<>();
        testList.add(monitor);
        underTest.save(monitor);

        List<Monitor> monitors = underTest.findByName("testing");

        assertEquals(testList.toString(), monitors.toString());
    }
}