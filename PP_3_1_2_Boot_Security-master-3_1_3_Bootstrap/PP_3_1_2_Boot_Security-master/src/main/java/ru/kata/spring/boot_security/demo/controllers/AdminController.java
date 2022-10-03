package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
public class AdminController {
    private final UserService usersServices;


    @Autowired
    public AdminController(UserService usersServices) {
        this.usersServices = usersServices;
    }

    @GetMapping("/api")
    public List<User> getAllUsers() {

        return usersServices.listUser();
    }

    @GetMapping("/api/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return usersServices.getUserById(id);
    }

    @PostMapping("/admins/api/new")
    public ResponseEntity<HttpStatus> createNewUser(@RequestBody User user) {
        usersServices.addUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    /*@GetMapping()
    public String showAllUsers(Principal principal, Model model) {
        model.addAttribute("users", usersServices.listUser());
        model.addAttribute("userAuth", usersServices.loadUserByUsername(principal.getName()));
        model.addAttribute("listRoles", usersServices.getAllRoles());
        return "admin";
    }

    @GetMapping("/new")
    public String createNewUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", usersServices.getAllRoles());
        return "new";
    }

    @PostMapping("/create")
    public String createNewUser(@ModelAttribute("user") User user){
        usersServices.addUser(user);
        return "redirect:/admins";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUsers(Model model) {
        model.addAttribute("listRoles", usersServices.getAllRoles());
        return "admin";
    }
    *//*@PatchMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.changeUser(id, user);
        return "redirect:/admin";
    }*//*
    @PatchMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        usersServices.updateUser(user);
        return "redirect:/admins";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", usersServices.getUserById(id));
        usersServices.removeUser(id);
        return "redirect:/admins";
    }*/
}
