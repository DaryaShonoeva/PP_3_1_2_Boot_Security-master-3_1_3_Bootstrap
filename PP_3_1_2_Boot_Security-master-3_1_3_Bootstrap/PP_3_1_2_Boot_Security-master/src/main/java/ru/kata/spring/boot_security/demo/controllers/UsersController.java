package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService usersServices;



    @Autowired
    public UsersController(UserService usersServices) {
        this.usersServices = usersServices;
    }


    @GetMapping("")
    public String showUserById(Principal principal, Model model) {

        model.addAttribute("users", usersServices.loadUserByUsername(principal.getName()));
        return "user";
    }

}

