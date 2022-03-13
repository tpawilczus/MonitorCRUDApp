package pl.edu.tpawilczus.jee.MonitorCRUDApp.service;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Producer;

public interface ProducerService {
    Iterable<Producer> allProducers();
    Producer addProducer(Producer producer);
    Producer findProducer(Long id);
    Producer updateProducer(Long id, Producer producer);
    void deleteProducer(Long id);
    Producer addMonitorToProducer(Long id, Long monitorId);
    Producer removeMonitorFromProducer(Long id, Long monitorId);
}