package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.dao.UserDaolmpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

@Component
public class MyUserDetailsService implements UserDetailsService {
  private final UserDao userDao;

  @Autowired
  public MyUserDetailsService(UserDaolmpl userService) {
    this.userDao = userService;
  }

  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = userDao.findByUserName(name);
    if (user == null) {
      throw new UsernameNotFoundException("User " + name + " not found");
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        mapRoles(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapRoles(Collection<Role> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
  }
}