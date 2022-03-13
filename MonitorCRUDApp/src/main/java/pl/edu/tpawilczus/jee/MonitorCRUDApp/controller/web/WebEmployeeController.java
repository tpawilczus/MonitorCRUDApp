package pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Employee;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.EmployeeDTO;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.MonitorService;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.EmployeeService;

import javax.validation.Valid;

@Controller
public class WebEmployeeController {

    private final EmployeeService employeeService;
    private final MonitorService monitorService;

    public WebEmployeeController(@Autowired EmployeeService employeeService, MonitorService monitorService) {
        this.employeeService = employeeService;
        this.monitorService = monitorService;
    }

    @GetMapping("/employee")
    public String employees(Model model) {
        model.addAttribute("allEmployeesFromDB", employeeService.allEmployees());
        return "employee-all";
    }

    @GetMapping("/employee/add")
    public String addNewEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("monitors", monitorService.allMonitors());
        return "employee-new";
    }

    @PostMapping("/employee/add")
    public String addEmployee(@ModelAttribute("employee") @Valid EmployeeDTO employee, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "employee-new";
        }
        else
            employeeService.addEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/edit/{id}")
    public String editEmployee(@PathVariable("id") Long id, Model model) {
        EmployeeDTO employeeToEdit = employeeService.findEmployee(id);
        model.addAttribute("employeeToEdit", employeeToEdit);
        model.addAttribute("monitors", monitorService.allMonitors());
        return "employee-edit";
    }

    @PostMapping("/employee/edit/{id}")
    public String editEmployee(@PathVariable("id") Long id, @ModelAttribute("employeeToEdit") @Valid EmployeeDTO employeeToEdit, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "employee-edit";
        }
        else
            employeeService.updateEmployee(id, employeeToEdit);
        return "redirect:/employee";
    }


    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee";
    }


}





