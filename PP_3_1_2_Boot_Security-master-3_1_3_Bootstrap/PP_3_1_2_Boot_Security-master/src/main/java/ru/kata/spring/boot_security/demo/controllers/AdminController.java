package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admins")
public class AdminController {
    private final UserService usersServices;


    @Autowired
    public AdminController(UserService usersServices) {
        this.usersServices = usersServices;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", usersServices.listUser());
        return "users";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("index", usersServices.getUserById(id));
        return "index";
    }

    @GetMapping("/new")
    public String createNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user){
        usersServices.addUser(user);
        return "redirect:/admins";
    }

    @GetMapping("/{id}/edit")
    public String editUsers(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", usersServices.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUsers(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        usersServices.updateUser(user);
        return "redirect:/admins";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUsers(@PathVariable("id") Long id) {
        usersServices.removeUser(id);
        return "redirect:/admins";
    }
}
