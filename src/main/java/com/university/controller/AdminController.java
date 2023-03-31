package com.university.controller;

import com.university.model.Group;
import com.university.model.Users;
import com.university.service.RoleService;
import com.university.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
@RolesAllowed({"ADMIN"})
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public ModelAndView getAllUser() {
        ModelAndView modelAndView = new ModelAndView("security/admin");
        modelAndView.addObject("userList", userService.findAllUser());

        return modelAndView;
    }

    @GetMapping("/changetemplate/{id}")
    public ModelAndView updateUserMVC(@PathVariable("id") long id) {
        Users userById = userService.findUserById(id);

        ModelAndView userMVC = new ModelAndView("security/userupdate");
        userMVC.addObject("userId", userById.getId());
        userMVC.addObject("userEmail", userById.getEmail());
        userMVC.addObject("userName", userById.getUserName());
        userMVC.addObject("rolesList", roleService.findAllRoles());

        return userMVC;
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute(name = "user") Users users) {
        userService.updateUser(users);

        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(userService.findUserById(id));

        return "redirect:/admin";
    }
}
