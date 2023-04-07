package com.university.controller;

import com.university.model.Users;
import com.university.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class SecurityController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("security/login");
    }

    @GetMapping("/register")
    public ModelAndView registerMVC() {
        return new ModelAndView("security/register");
    }

    @PostMapping("/reg")
    public void addUser(@ModelAttribute(name = "users") Users users) {
        System.out.println("users.getPassword() = " + users.getPassword());

        userService.createUser(users);

//        return "redirect:/login";
    }
}
