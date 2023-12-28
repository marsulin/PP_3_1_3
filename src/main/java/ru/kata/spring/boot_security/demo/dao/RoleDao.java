package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;


import java.util.List;
import ru.kata.spring.boot_security.demo.model.Role;

@Repository
public interface RoleDao {
  List<Role> getRoles();
}
