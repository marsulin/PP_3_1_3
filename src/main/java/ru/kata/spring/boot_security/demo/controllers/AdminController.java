package ru.kata.spring.boot_security.demo.controllers;

import jakarta.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
  private final UserService userService;
  private final RoleService roleService;

  @Autowired
  public AdminController(UserService userService, RoleService roleService) {
    this.userService = userService;
    this.roleService = roleService;
  }

  //ALL
  @GetMapping(value = "/")
  public String getAllUsers(ModelMap model, Principal principal) {
    User user = userService.findByUserName(principal.getName());
    model.addAttribute("user", user);
    List<User> listOfUsers = userService.getAllUsers();
    model.addAttribute("listOfUsers", listOfUsers);
    return "users";
  }

  // CREATE
  @GetMapping("/new")
  public String CreateUserForm(ModelMap model) {
    User user = new User();
    model.addAttribute("user", user);
    Collection<Role> roles = roleService.getRoles();
    model.addAttribute("role", roles);
    return "userCreate";
  }

  @PostMapping("/")
  public String addUser(@ModelAttribute("user") @Valid User user, ModelMap model) {
    model.addAttribute("roles", roleService.getRoles());
    userService.addUser(user);
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userService.updateUser(user);

    return "redirect:/admin/";
  }

  // UPDATE
  @GetMapping("/{id}/update")
  public String getEditUserForm(ModelMap model, @PathVariable("id") Long id) {
    model.addAttribute("user", userService.getUser(id));
    return "userUpdate";
  }

  @PatchMapping("/{id}")
  public String saveUpdateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
    userService.updateUser(user);
    return "redirect:/admin/";
  }

  // DELETE
  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable("id") Long id) {
    userService.removeUser(id);
    return "redirect:/admin/";
  }
}