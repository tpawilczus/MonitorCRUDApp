package pl.edu.tpawilczus.jee.MonitorCRUDApp.service;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;

public interface MonitorService {
    Iterable<Monitor> allMonitors();
    Monitor addMonitor(Monitor monitor);
    Monitor findMonitor(Long id);
    Monitor updateMonitor(Long id, Monitor monitor);
    void deleteMonitor(Long id);
    void enterSomeData();
    Monitor setUserToMonitor(Long id, Long userId);
    Monitor removeUserFromMonitor(Long id);

}