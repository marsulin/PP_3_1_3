package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

@Service
public class RoleServicelmpl implements RoleService {
  private final RoleDao roleDao;

  @Autowired
  public RoleServicelmpl(RoleDao roleDao) {
    this.roleDao = roleDao;
  }

  @Override
  public List<Role> getRoles() {
    return roleDao.getRoles();
  }
}
