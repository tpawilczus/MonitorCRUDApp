package pl.edu.tpawilczus.jee.MonitorCRUDApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.api.ProducerNotFoundException;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Producer;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.ProducerRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;
    private final MonitorService monitorService;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository, MonitorService monitorService) {
        this.producerRepository = producerRepository;
        this.monitorService = monitorService;
    }

    public Producer addProducer(Producer producer) {
        return producerRepository.save(producer);
    }

    public Iterable<Producer> allProducers() {
        return producerRepository.findAll();
    }

    public Producer findProducer(Long id) {
        return producerRepository.findById(id).orElseThrow(() -> new ProducerNotFoundException());
    }

    public Producer updateProducer(Long id, Producer producer) {
        Optional<Producer> producerToUpdate = producerRepository.findById(id);
        Producer _producer = producerToUpdate.get();
        _producer.setName(producer.getName());
        _producer.setCountry(producer.getCountry());
        _producer.setYof(producer.getYof());
        _producer.setMonitors(producer.getMonitors());
        return producerRepository.save(_producer);
    }

    public void deleteProducer(Long id) {
        producerRepository.deleteById(id);
    }

    public Producer addMonitorToProducer(Long id, Long monitorId) {
        Producer producer = findProducer(id);
        Monitor monitor = monitorService.findMonitor(monitorId);
        producer.addMonitor(monitor);
        return producerRepository.save(producer);
    }

    public Producer removeMonitorFromProducer(Long id, Long monitorId) {
        Producer producer = findProducer(id);
        Monitor monitor = monitorService.findMonitor(monitorId);
        producer.removeMonitor(monitor);
        return producerRepository.save(producer);
    }
}


