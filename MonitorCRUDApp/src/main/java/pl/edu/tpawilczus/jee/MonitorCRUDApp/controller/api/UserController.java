package pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.Monitor;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.User;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/api/user")
    public Iterable<User> allUsers() {
        return userService.allUsers();
    }

    @GetMapping("api/user/{id}")
    public User findUser(@PathVariable("id") Long id) {
        return userService.findUser(id);
    }

    @PutMapping("/api/user/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("api/user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
