package pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.User;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.UserService;

import javax.validation.Valid;

@Controller
public class WebUserController {

    private final UserService userService;

    public WebUserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String users(Model model) {
        model.addAttribute("allUsersFromDB", userService.allUsers());
        return "user-all";
    }

    @GetMapping("/user/add")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-new";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user-new";
        }
        else
            userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User userToEdit = userService.findUser(id);
        model.addAttribute("userToEdit", userToEdit);
        return "user-edit";
    }

    @PostMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute("userToEdit") @Valid User userToEdit, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "user-edit";
        }
        else
            userService.updateUser(id, userToEdit);
        return "redirect:/user";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}
