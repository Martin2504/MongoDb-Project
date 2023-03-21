package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.model.entities.User;
import com.sparta.spartamongodbfinalproject.model.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersWebController {

    private UserRepository userRepository;

    @Autowired
    public UsersWebController(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    @GetMapping("/users")
    public String getUsers(){
        return "users/users-search-form";
    }

    @GetMapping("/users/search/")
    public String getUserDetails(Model model, @RequestParam String name) {
        model.addAttribute("users", userRepository.findUsersByName(name));
        return "users/users-search-results";
    }


    @GetMapping("/users/delete/{id}/{name}")
    public String deleteUser(@PathVariable String id, @PathVariable String name) {
        User users = new User();
        users.setId(id);
        users.setName(name);
        userRepository.deleteById(id);
        return "users/users-delete-success";
    }


    @GetMapping("/users/edit/{id}")
    public String getUserToEdit(Model model, @PathVariable String id) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("userToEdit", user);
        return "users/users-edit-form";
    }


    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("userToEdit")User editedUser) {
        userRepository.save(editedUser);
        return "users/users-edit-success";
    }


    @GetMapping("/users/create")
    public String getUserToCreate() {
        return "users/users-create-form";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("userToCreate") User createdUser) {
        userRepository.save(createdUser);
        return "users/new-user-success";
    }


}
