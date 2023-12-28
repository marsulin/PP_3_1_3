package ru.kata.spring.boot_security.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
  @GetMapping("/")
  public String sayHello(ModelMap modelMap) {
    List<String> messages = new ArrayList<>();
    messages.add("Happy New Year!");
    messages.add("Click the Start button for authorization!");
    modelMap.addAttribute("messages", messages);
    return "hello";
  }
}