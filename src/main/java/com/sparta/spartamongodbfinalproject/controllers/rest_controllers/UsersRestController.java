package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;

import com.sparta.spartamongodbfinalproject.model.repositories.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersRestController {

    private final UsersRepositories usersRepositories;

    @Autowired
    public UsersRestController(UsersRepositories usersRepositories) {
        this.usersRepositories = usersRepositories;
    }

    @PostMapping()



}
