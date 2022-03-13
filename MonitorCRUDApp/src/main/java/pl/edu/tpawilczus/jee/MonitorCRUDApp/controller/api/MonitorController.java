package pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Producer;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.MonitorService;

import java.util.Optional;

@RestController
public class MonitorController {

    private final MonitorService monitorService;

    public MonitorController(@Autowired MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @PostMapping("/api/monitor")
    public Monitor addMonitor(@RequestBody Monitor monitor) {
        return monitorService.addMonitor(monitor);
    }

    @GetMapping("/api/monitor")
    public Iterable<Monitor> allMonitors() {
        return monitorService.allMonitors();
    }

    @GetMapping("api/monitor/{id}")
    public Monitor findMonitor(@PathVariable("id") Long id) {
        return monitorService.findMonitor(id);
    }

    @PutMapping("/api/monitor/{id}")
    public Monitor updateMonitor(@PathVariable("id") Long id, @RequestBody Monitor monitor) {
        return monitorService.updateMonitor(id, monitor);
    }

    @DeleteMapping("api/monitor/{id}")
    public void deleteMonitor(@PathVariable("id") Long id) {
        monitorService.deleteMonitor(id);
    }

    @PostMapping("/api/monitor/{id}/user/{userId}")
    public Monitor setUserToMonitor(@PathVariable("id") Long id, @PathVariable("userId") Long userId ) {
        return monitorService.setUserToMonitor(id, userId);
    }

    @DeleteMapping("/api/monitor/{id}/user")
    public Monitor removeUserFromMonitor(@PathVariable("id") Long id) {
        return monitorService.removeUserFromMonitor(id);
    }


}
