package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.entities.Schedule;
import com.sparta.spartamongodbfinalproject.model.entities.Theatre;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.ScheduleRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.TheatreRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ScheduleRestController {

    private final ScheduleRepository scheduleRepository;
    private final TheatreRepository theatreRepository;
    private final MovieRepository movieRepository;
    private final ObjectMapper mapper;

    @Autowired
    public ScheduleRestController(ScheduleRepository scheduleRepository, TheatreRepository theatreRepository, MovieRepository movieRepository, ObjectMapper mapper) {
        this.scheduleRepository = scheduleRepository;
        this.theatreRepository = theatreRepository;
        this.movieRepository = movieRepository;
        this.mapper = mapper;
    }

    @GetMapping(value = "/api/schedules")
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }


    @GetMapping(value = "/api/schedules/object/{objectId}")
    public ResponseEntity<String> getAllSchedulesByObjectId(@PathVariable String objectId) {
        Optional<Schedule> schedule = scheduleRepository.findScheduleById(objectId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (schedule.isPresent()) {
            ResponseEntity<String> response = null;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString(schedule), httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response;
        }
        ResponseEntity<String> scheduleNotFound = new ResponseEntity<>("{\"message:\":\"No Schedule with that ID\"}", httpHeaders, HttpStatus.NOT_FOUND);
        return scheduleNotFound;
    }

    @GetMapping(value = "/api/schedules/theatre/{theatreId}")
    public ResponseEntity<String> getAllSchedulesByTheatreId(@PathVariable ObjectId theatreId) {
        Optional<Schedule> schedule = scheduleRepository.findScheduleByTheatreId(theatreId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (schedule != null) {
            ResponseEntity<String> response = null;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString(schedule), httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response;
        }
        ResponseEntity<String> schedulesNotFound = new ResponseEntity<>("{\"message:\":\"There are no schedules in the database\"}", httpHeaders, HttpStatus.NOT_FOUND);
        return schedulesNotFound;
    }


    @GetMapping(value = "/api/schedules/search")
    public ResponseEntity<String> getAllSchedulesByMovieId(@RequestParam String title, @RequestParam Integer year) {
        Optional<Movie> movie = movieRepository.findMovieByTitleAndYear(title, year);
        List<Schedule> schedule = scheduleRepository.findSchedulesByMovie_Id(movie.orElseThrow().getId());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (schedule != null) {
            ResponseEntity<String> response;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString(schedule), httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response;

        }
        ResponseEntity<String> scheduleNotFound = new ResponseEntity<>("{\"message:\":\"No schedules with that title and year\"}", httpHeaders, HttpStatus.NOT_FOUND);
        return scheduleNotFound;

    }


    @PostMapping("/api/schedules/create")
    public ResponseEntity<String> CreateSchedule(@RequestBody Schedule schedule, @RequestBody String movieId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        Movie movie = movieRepository.findById(movieId).orElse(null);
        scheduleRepository.save(schedule);
        try {
            return new ResponseEntity<>(mapper.writeValueAsString("New Schedule has been added"), httpHeaders, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


//    @PutMapping(value = "api/schedules/edit/{id}")
//    public ResponseEntity<String> updateScheduleById(@PathVariable String id, @RequestBody Schedule schedule){
//        Optional<Schedule> scheduleToEdit = scheduleRepository.findScheduleById(id);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("content-type", "application/json");
//        if(scheduleToEdit.isPresent()){
//            ResponseEntity<String> response = null;
//            try{ response = new ResponseEntity<>(mapper.writeValueAsString("will be updated"), httpHeaders, HttpStatus.OK);
//        } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//    }
}










