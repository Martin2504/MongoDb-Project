package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
//import com.sparta.spartamongodbfinalproject.model.entities.Movie;

import com.sparta.spartamongodbfinalproject.model.entities.Movie;

import com.sparta.spartamongodbfinalproject.logicalOperator.StringToArrayString;
import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.entities.movies.*;

import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller()
@RequestMapping("/web")
public class MoviesWebController {
    private MovieRepository movieRepository;
    private Tomato tomato;

    @Autowired
    public MoviesWebController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //find all

    @GetMapping("/movies")
    public String getAllMovies(Model model){

        SpartaMongoDbFinalProjectApplication.logger.info("before");

        List<Movie> movieList = movieRepository.findAll(PageRequest.of(1, 2)).toList();

        SpartaMongoDbFinalProjectApplication.logger.info(movieList.toString());
        model.addAttribute("movies",movieList);

        return "movies/movies";
    }


//    //create
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/movie/create")
    public String createMovie(Model model) {
        Movie newMovie=new Movie();
        newMovie.setLastupdated(String.valueOf(LocalDateTime.now()));
        model.addAttribute("nowDate", newMovie);
        return "movies/movie-add-form";
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @PostMapping("/createMovie")
    public String createMovie(@ModelAttribute("movieToCreate") Movie addedMovie,
//
                              Award awards,
                              Imdb imdb,
                              Tomato tomato,
                              Viewer viewer,
                              Critic critic
                              ) {

        addedMovie.setAwards(awards);
        addedMovie.setImdb(imdb);
        tomato.setViewer(viewer);
        tomato.setCritic(critic);
        tomato.setTomato_lastUpdated(LocalDateTime.now());
        tomato.setTomato_lastUpdated(LocalDateTime.now());
        addedMovie.setTomatoes(tomato);
        addedMovie.setLastupdated(LocalDateTime.now().toString());
        SpartaMongoDbFinalProjectApplication.logger.info(addedMovie.toString());
        SpartaMongoDbFinalProjectApplication.logger.info(addedMovie.getId());
        movieRepository.save(addedMovie);
        return "movies/success";
        //entry with id to be deleted: 473a1390f29313caabcd42e8
        //Date problem, tomato last_releaseDate
       //Id don't autogenerate
    }


    //Read
//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/movie/find")
    public String findMovie() {
        return "movies/movie-find-form";
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/findMovieById")

    public String findMovieById(@ModelAttribute("movieToFind")Movie foundMovie,Model model) {
        SpartaMongoDbFinalProjectApplication.logger.info(foundMovie.toString());
        String id=foundMovie.getId();
        List<Movie> movies;
        if(id!=null){
            Movie movie=movieRepository.findMoviesById(id);
            movies=new ArrayList<Movie>();
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
    @GetMapping("/movie/delete/{id}")
    public String deleteMovie(@PathVariable String id) {
        movieRepository.deleteById(id);
        return "movies/success";
    }


    @GetMapping("/movie/edit/{id}")
    public String movieToEdit(Model model, @PathVariable String id) {
        Movie movie = movieRepository.findMoviesById(id);
        this.tomato=movie.getTomatoes();
        SpartaMongoDbFinalProjectApplication.logger.info("this.tomato: "+this.tomato.toString());
        model.addAttribute("movieToEdit", movie);
        return "/movies/movie-edit-form";
    }



    @PostMapping("/editMovie")
    public String editMovie(@ModelAttribute("movieToEdit") Movie editedMovie) {
        Tomato editedTomato=editedMovie.getTomatoes();
        editedTomato.setTomato_lastUpdated(this.tomato.getTomato_lastUpdated());
        //if there is change made to tomato
        if(this.tomato==null&&editedTomato!=null ){
            editedTomato.setTomato_lastUpdated(LocalDateTime.now());
            SpartaMongoDbFinalProjectApplication.logger.info("editedTomato: "+editedTomato.toString());
            SpartaMongoDbFinalProjectApplication.logger.info("setLastUpdateTomato called 1");
        } else if(this.tomato!=null&& editedTomato!=null
                && !this.tomato.toString().equals(editedTomato.toString())){
            SpartaMongoDbFinalProjectApplication.logger.info(this.tomato.toString());
            SpartaMongoDbFinalProjectApplication.logger.info(editedTomato.toString());
            SpartaMongoDbFinalProjectApplication.logger.info("setLastUpdateTomato called 2");

            editedTomato.setTomato_lastUpdated(LocalDateTime.now());
        }
        editedMovie.setLastupdated(String.valueOf(LocalDateTime.now()));
        editedMovie.setTomatoes(editedTomato);
        movieRepository.save(editedMovie);

        return "/movies/movie-edit-success";
    }

    @GetMapping("/movies/home")
    public String moviesHome() {
        return "mainPages/movies-page";
    }
}
