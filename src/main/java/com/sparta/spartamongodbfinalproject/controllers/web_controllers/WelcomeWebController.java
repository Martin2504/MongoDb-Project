package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WelcomeWebController {
    // The welcome page
    @GetMapping("/")
    public String welcome() {
        return "../static/welcome" ;
    }

    // Comments Page
    @GetMapping("/commentsPage")
    public String departmentPage() {
        return "mainPages/comments-page";
    }

    // Movies Page
    @GetMapping("/moviesPage")
    public String departmentEmployeePage() {
        return "mainPages/movies-page";
    }

    // Sessions Page
    @GetMapping("/sessionsPage")
    public String departmentManagerPage() {
        return "mainPages/sessions-page";
    }

    // Theaters Page
    @GetMapping("/theatresPage")
    public String employeesPage() {
        return "mainPages/theatres-page";
    }

    // Users Page
    @GetMapping("/usersPage")
    public String salariesPage() {
        return "mainPages/users-page";
    }

}
