package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserDao {
  User findByUserName(String name);

  User getUser(Long id);

  List<User> getAllUsers();

  void addUser(User user);

  void removeUser(Long id);

  void updateUser(User user);

}
