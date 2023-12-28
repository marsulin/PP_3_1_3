package ru.kata.spring.boot_security.demo.service;



import java.util.List;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserService {
  User findByUserName(String name);

  User getUser(Long id);

  List<User> getAllUsers();

  void addUser(User user);

  void removeUser(Long id);

  void updateUser(User user);

}
