package pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Producer;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.MonitorService;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.ProducerService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WebProducerController {

    private final ProducerService producerService;
    private final MonitorService monitorService;

    public WebProducerController(@Autowired ProducerService producerService, MonitorService monitorService) {
        this.producerService = producerService;
        this.monitorService = monitorService;
    }

    @GetMapping("/producer")
    public String producers(Model model) {
        model.addAttribute("allProducersFromDB", producerService.allProducers());
        return "producer-all";
    }

    @GetMapping("/producer/add")
    public String addNewProducer(Model model) {
        Producer producer = new Producer();
        model.addAttribute("producer", producer);
        model.addAttribute("monitors", monitorService.allMonitors());
        return "producer-new";
    }

    @PostMapping("/producer/add")
    public String addProducer(@ModelAttribute("producer") @Valid Producer producer, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "producer-new";
        }
        else
            producerService.addProducer(producer);
        return "redirect:/producer";
    }

    @GetMapping("/producer/edit/{id}")
    public String editProducer(@PathVariable("id") Long id, Model model) {
        Producer producerToEdit = producerService.findProducer(id);
        model.addAttribute("producerToEdit", producerToEdit);
        model.addAttribute("monitors", producerToEdit.getMonitors());
        return "producer-edit";
    }

    @PostMapping("/producer/edit/{id}")
    public String editProducer(@PathVariable("id") Long id, @ModelAttribute("producerToEdit") @Valid Producer producerToEdit, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "producer-edit";
        }
        else
            producerService.updateProducer(id, producerToEdit);
        return "redirect:/producer";
    }

    @GetMapping("/producer/delete/{id}")
    public String deleteProducer(@PathVariable("id") Long id) {
        producerService.deleteProducer(id);
        return "redirect:/producer";
    }


}



