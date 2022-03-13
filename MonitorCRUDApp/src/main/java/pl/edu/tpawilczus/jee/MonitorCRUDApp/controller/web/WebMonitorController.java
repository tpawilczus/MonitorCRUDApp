package pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.User;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.MonitorService;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.UserService;

import javax.validation.Valid;

@Controller
public class WebMonitorController {

    private final MonitorService monitorService;
    private final UserService userService;

    public WebMonitorController(@Autowired MonitorService monitorService, UserService userService) {
        this.monitorService = monitorService;
        this.userService = userService;
    }

    @GetMapping("/monitor")
    public String monitors(Model model) {
        model.addAttribute("allMonitorsFromDB", monitorService.allMonitors());
        return "monitor-all";
    }

    @GetMapping("/monitor/add")
    public String addNewMonitor(Model model) {
        Monitor monitor = new Monitor();
        model.addAttribute("monitor", monitor);
        model.addAttribute("users", userService.allUsers());
        return "monitor-new";
    }

    @PostMapping("/monitor/add")
    public String addMonitor(@ModelAttribute("monitor") @Valid Monitor monitor, BindingResult bindingResult,
                             @ModelAttribute("users") @Valid User user) {
        if(bindingResult.hasErrors()) {
            return "monitor-new";
        }
        else {
            System.out.println("*********************************");
            monitorService.addMonitor(monitor);
            if(user.getId() != null)
                monitorService.setUserToMonitor(monitor.getId(), user.getId());
        }

        return "redirect:/monitor";
    }

    @GetMapping("/monitor/edit/{id}")
    public String editMonitor(@PathVariable("id") Long id, Model model) {
        Monitor monitorToEdit = monitorService.findMonitor(id);
        model.addAttribute("monitorToEdit", monitorToEdit);
        return "monitor-edit";
    }

    @PostMapping("/monitor/edit/{id}")
    public String editMonitor(@PathVariable("id") Long id, @ModelAttribute("monitorToEdit") @Valid Monitor monitorToEdit, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "monitor-edit";
        }
        else
            monitorService.updateMonitor(id, monitorToEdit);
        return "redirect:/monitor";
    }


    @GetMapping("/monitor/delete/{id}")
    public String deleteMonitor(@PathVariable("id") Long id) {
        monitorService.deleteMonitor(id);
        return "redirect:/monitor";
    }
}
