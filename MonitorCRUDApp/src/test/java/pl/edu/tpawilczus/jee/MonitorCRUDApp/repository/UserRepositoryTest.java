package pl.edu.tpawilczus.jee.MonitorCRUDApp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;
    @Autowired
    private MonitorRepository monitorRepository;

    @Test
    void findByMonitorNameIsEndingWithOrderByYob() {
        User user = new User("Maciek", "Kasprzyk", 1990);
        Monitor monitor = new Monitor("testing", 60, 70);
        underTest.save(user);
        monitorRepository.save(monitor);
        user.setMonitor(monitor);
        underTest.save(user);
        User testUser = underTest.findByMonitorNameIsEndingWithOrderByYob("ing").get(0);
        assertEquals(user.getFirstName(), testUser.getFirstName());
    }
}