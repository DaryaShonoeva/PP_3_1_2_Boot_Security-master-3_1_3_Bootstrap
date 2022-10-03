package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/admins")
public class AdminController {
    private final UserService usersServices;


    @Autowired
    public AdminController(UserService usersServices) {
        this.usersServices = usersServices;
    }
    @GetMapping()
    public List<User> showAllUsers() {
        return usersServices.listUser();
    }

    @PostMapping("/admins/new")
    public ResponseEntity<HttpStatus> createNewUser(@RequestBody User user) {
        usersServices.addUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user) {
        usersServices.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        usersServices.removeUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(usersServices.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<User> showUser(@PathVariable Long id) {
        User user = usersServices.getUserById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
