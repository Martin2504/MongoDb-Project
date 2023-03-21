package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
//import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.logicalOperator.StringToArrayString;
import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.entities.movies.Award;
import com.sparta.spartamongodbfinalproject.model.entities.movies.Imdb;
import com.sparta.spartamongodbfinalproject.model.entities.movies.Tomato;
import com.sparta.spartamongodbfinalproject.model.entities.movies.Viewer;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
public class MoviesWebController {

    private MovieRepository moviesRepository;

    @Autowired
    public MoviesWebController(MovieRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    //find all

    @GetMapping("/movies")
    public String getAllMovies(Model model){
        List<Movie> movieList =moviesRepository.findAll().subList(0,1);
        SpartaMongoDbFinalProjectApplication.logger.info(movieList.toString());
        model.addAttribute("movies", movieList);
        return "movies/movies";
    }


//    //create
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/movie/create")
    public String createMovie(Model model) {
        Date date = new Date(System.currentTimeMillis());

// Conversion
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        String text = sdf.format(date);
        model.addAttribute("nowDate", text);
        return "movies/movie-add-form";
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/createMovie")
    public String createMovie(@ModelAttribute("movieToCreate") Movie addedMovie,
                              String genresList, String castList,
                              String countriesList, String directorsList,
                              Award awards,
                              Imdb imdb,
                              Tomato tomato,
                              Viewer viewer
                              ) {
        addedMovie.setGenres(StringToArrayString.convert(genresList));
        addedMovie.setCast(StringToArrayString.convert(castList));
        addedMovie.setCountries(StringToArrayString.convert(countriesList));
        addedMovie.setDirectors(StringToArrayString.convert(directorsList));
        addedMovie.setAwards(awards);
        addedMovie.setImdb(imdb);
        tomato.setViewer(viewer);
        addedMovie.setTomatoes(tomato);
        SpartaMongoDbFinalProjectApplication.logger.info(addedMovie.toString());
        moviesRepository.save(addedMovie);
        return "movies/success";
        //entry with id to be deleted: 473a1390f29313caabcd42e8
        //Date problem
        //change release data type to date
        //year data type to integer or date year
    }


    //Read
//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/movie/find")
    public String findMovie() {
        return "movies/movie-find-form";
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/findMovieById")
    public String findMovieById(@ModelAttribute("movieToFind") Movie foundMovie, Model model
    ) {
        SpartaMongoDbFinalProjectApplication.logger.info(foundMovie.toString());
        String id=foundMovie.getId();
        List<Movie> movies;
        if(id!=null){
            Movie movie=moviesRepository.findMoviesById(id);
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
//    @GetMapping("/employee/delete/{id}")
//    public String deleteEmployee(@PathVariable Integer id) {
//        moviesRepository.deleteById(id);
//        return "fragments/delete-success";
//    }

}
