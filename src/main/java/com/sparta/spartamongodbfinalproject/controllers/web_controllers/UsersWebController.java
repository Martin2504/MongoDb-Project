package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.model.entities.Users;
import com.sparta.spartamongodbfinalproject.model.repositories.UsersRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsersWebController {

    private UsersRepository usersRepository;

    @Autowired
    public UsersWebController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users")
    public String getUsers(){
        return "users/users-search-form";
    }

    @GetMapping("/users/search/")
    public String getUserDetails(Model model, @RequestParam String name) {
        model.addAttribute("users", usersRepository.findByName(name));
        return "users/users-search-results";
    }


    @GetMapping("/users/delete/{id}/{name}")
    public String deleteUser(@PathVariable ObjectId id, @PathVariable String name) {
        Users users = new Users();
        users.setId(id);
        users.setName(name);
        usersRepository.deleteById(id);
        return "users/users-delete-success";
    }


    @GetMapping("/users/edit/{id}")
    public String getUserToEdit(Model model, @PathVariable ObjectId id) {
        Users user = usersRepository.findById(id).orElse(null);
        model.addAttribute("userToEdit", user);
        return "users/users-edit-form";
    }


    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("userToEdit")Users editedUser) {
        usersRepository.save(editedUser);
        return "users/users-edit-success";
    }


    @GetMapping("/users/create")
    public String getUserToCreate() {
        return "users/users-create-form";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("userToCreate") Users createdUser) {
        usersRepository.save(createdUser);
        return "users/new-user-success";
    }





}
