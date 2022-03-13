package pl.edu.tpawilczus.jee.MonitorCRUDApp.service;

import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.User;

public interface UserService {
    Iterable<User> allUsers();
    User addUser(User user);
    User findUser(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
