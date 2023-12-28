package ru.kata.spring.boot_security.demo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.dao.UserDaolmpl;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
public class UserController {
  private final UserDaolmpl userDaolmpl;

  @Autowired
  public UserController(UserDaolmpl userDaoImpl) {
    this.userDaolmpl = userDaoImpl;
  }

  @GetMapping("/user")
  public String userPage(Model model, Principal principal) {
    User user = userDaolmpl.findByUserName(principal.getName());
    model.addAttribute("user", user);
    return "user";
  }
}
