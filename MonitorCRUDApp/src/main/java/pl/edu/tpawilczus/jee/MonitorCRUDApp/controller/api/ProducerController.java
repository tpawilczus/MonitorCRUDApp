package pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Producer;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.ProducerService;

@RestController
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(@Autowired ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/api/producer")
    public Producer addProducer(@RequestBody Producer producer) {
        return producerService.addProducer(producer);
    }

    @GetMapping("/api/producer")
    public Iterable<Producer> allProducers() {
        return producerService.allProducers();
    }

    @GetMapping("api/producer/{id}")
    public Producer findProducer(@PathVariable("id") Long id) {
        return producerService.findProducer(id);
    }

    @PutMapping("/api/producer/{id}")
    public Producer updateProducer(@PathVariable("id") Long id, @RequestBody Producer producer) {
        return producerService.updateProducer(id, producer);
    }

    @DeleteMapping("api/producer/{id}")
    public void deleteProducer(@PathVariable("id") Long id) {
        producerService.deleteProducer(id);
    }

    @PostMapping("/api/producer/{id}/monitor/{monitorId}")
    public Producer addMonitorToProducer(@PathVariable("id") Long id, @PathVariable("monitorId") Long monitorId ) {
        return producerService.addMonitorToProducer(id, monitorId);
    }

    @DeleteMapping("/api/producer/{id}/monitor/{monitorId}")
    public Producer removeMonitorFromProducer(@PathVariable("id") Long id, @PathVariable("monitorId") Long monitorId) {
        return producerService.removeMonitorFromProducer(id, monitorId);
    }
}



