package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
//import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.entities.Movies;
import com.sparta.spartamongodbfinalproject.model.repositories.MoviesRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MoviesWebController {

    private MoviesRepository moviesRepository;

    @Autowired
    public MoviesWebController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    //find all

    @GetMapping("/movies")
    public String getAllMovies(Model model){
        List<Movies> moviesList=moviesRepository.findAll().subList(0,1);
        SpartaMongoDbFinalProjectApplication.logger.info(moviesList.toString());
        model.addAttribute("movies",moviesList);
        return "movies/movies";
    }


//    //create
////    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping("/employee/create")
//    public String createEmployee() {
//        return "employee/employee-add-form";
//    }
//
////    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping("/createEmployee")
//    public String createEmployee(@ModelAttribute("employeeToCreate")Movies addedEmployee) {
//        moviesRepository.save(addedEmployee);
//        return "fragments/create-success";
//    }
//
//
    //Read
//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/movie/find")
    public String findMovie() {
        return "movies/movie-find-form";
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/findMovieById")
    public String findMovieById(@ModelAttribute("movieToFind")Movies foundMovie,Model model
    ) {
        SpartaMongoDbFinalProjectApplication.logger.info(foundMovie.toString());
        String id=foundMovie.getId();
        List<Movies> movies;
        if(id!=null){
            Movies movie=moviesRepository.findMoviesById(id);
            movies=new ArrayList<Movies>();
            SpartaMongoDbFinalProjectApplication.logger.info(movie.toString());



            if (movie != null) {
                movies.add(movie);
                model.addAttribute("movies",movies);
            }
        } else {
            model.addAttribute("movies",null);

        }
        return "movies/movie";
    }
//
////    @PreAuthorize("hasRole('ROLE_USER')")
//    @PostMapping("/findEmployeeByName")
//    public String findEmployeeByName(@ModelAttribute("employeeToFind")Movies foundEmployee,Model model
//    ) {
//        SpartaMongoDbFinalProjectApplication.logger.info(foundEmployee.toString());
//        String firstName=foundEmployee.getFirstName();
//        String lastName=foundEmployee.getLastName();
//        SpartaMongoDbFinalProjectApplication.logger.info(firstName);
//        SpartaMongoDbFinalProjectApplication.logger.info(lastName);
//        List<Employee> employees;
//        if(!firstName.equals("") && !lastName.equals("")) {
//            employees=moviesRepository.findByFirstNameAndAndLastName(firstName,lastName);
//        } else if(!firstName.equals("")){
//            employees=moviesRepository.findByFirstName(firstName);
//            SpartaMongoDbFinalProjectApplication.logger.info(employees.toString());
//        } else if(!lastName.equals("")){
//            employees=moviesRepository.findByFirstName(lastName);
//        }else {
//            employees=new ArrayList<Movies>();
//        }
//
//        if(employees.size()==0){
//            model.addAttribute("employees",null);
//        } else {
//            model.addAttribute("employees",employees);
//        }
//        return "employee/employee";
//    }
//
////    @PreAuthorize("hasRole('ROLE_USER')")
//    @PostMapping("/findEmployeeByDepartAndDate")
//    public String findEmployeeByDeptNameAndDate(LocalDate fromDate, LocalDate toDate, String deptName,
//                                                Model model
//    ) {
//        List<Movies> employees=moviesRepository.getEmployeesByDateAndDepartment(fromDate,toDate,deptName);
//        if(employees.size()==0){
//            model.addAttribute("employees",null);
//        } else {
//            model.addAttribute("employees",employees);
//        }
//        return "employee/employee";
//    }
//    //update
////    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping("/employee/edit/{id}")
//    public String getEmployeeToEdit(@PathVariable Integer id, Model model) {
//        Movies employee = moviesRepository.findById(id).orElse(null);
//        model.addAttribute("employeeToEdit", employee);
//        return "employee/employee-edit-form";
//    }
//
////    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping("/updateEmployee")
//    public String updateEmployee(@ModelAttribute("employeeToEdit")Movies editedEmployee) {
//        moviesRepository.saveAndFlush(editedEmployee);
//        return "fragments/edit-success";
//    }
//
//    //delete
////    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping("/employee/delete/{id}")
//    public String deleteEmployee(@PathVariable Integer id) {
//        moviesRepository.deleteById(id);
//        return "fragments/delete-success";
//    }

}
