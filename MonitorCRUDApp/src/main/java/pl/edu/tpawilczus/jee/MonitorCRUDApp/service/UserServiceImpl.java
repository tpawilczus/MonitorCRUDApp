package pl.edu.tpawilczus.jee.MonitorCRUDApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.api.UserNotFoundException;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.domain.User;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }

    public User updateUser(Long id, User user) {
        Optional<User> userToUpdate = userRepository.findById(id);
        User _user = userToUpdate.get();
        _user.setFirstName(user.getFirstName());
        _user.setLastName(user.getLastName());
        _user.setYob(user.getYob());
        _user.setMonitor(user.getMonitor());

        return userRepository.save(_user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
