package org.jcd2052.controllers;

import org.jcd2052.models.UserInfo;
import org.jcd2052.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final String USER_INFO = "user";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute(USER_INFO) UserInfo user) {
        return "users/add";
    }

    @PostMapping
    public String addUser(@ModelAttribute(USER_INFO) UserInfo user) {
        userService.save(user);
        return "redirect:/games";
    }


}
