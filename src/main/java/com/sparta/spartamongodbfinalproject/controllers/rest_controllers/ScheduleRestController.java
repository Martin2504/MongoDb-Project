
package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
import com.sparta.spartamongodbfinalproject.model.entities.Comment;
import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.entities.Schedule;
import com.sparta.spartamongodbfinalproject.model.entities.Theatre;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Showings;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.ScheduleRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.TheatreRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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


    @GetMapping(value = "/api/schedules/searchByMovieId")
    public ResponseEntity<String> getAllSchedulesByMovieId(@RequestParam String movieId) {
        List<Schedule> schedules = scheduleRepository.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");

        List<Schedule> tempSchedules = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ArrayList<Showings> tempShowings = new ArrayList<>();
            List<Showings> showings = schedule.getShowings();
            for (Showings s : showings) {
                if (s.getMovie().getId().equals(movieId)) {
                    tempShowings.add(s);
                }
            }
            schedule.setShowings(tempShowings);
            tempSchedules.add(schedule);

        }

        int startSize = schedules.size();
        List<Schedule> finalSchedules = new ArrayList<>();

        for (int i = 0; i < startSize; i++) {
            if (!schedules.get(i).getShowings().isEmpty()) {
                finalSchedules.add(tempSchedules.get(i));
            }
        }
        if (!finalSchedules.isEmpty()) {
            ResponseEntity<String> response;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString(finalSchedules), httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response;
        } else {
            ResponseEntity<String> schedulesNotFound = new ResponseEntity<>("{\"message:\":\"There are no schedules with that movie ID\"}", httpHeaders, HttpStatus.NOT_FOUND);
            return schedulesNotFound;
        }
    }


    @GetMapping(value = "/api/schedules/searchByTheatreId")
    public ResponseEntity<String> getAllSchedulesByTheatreId(@RequestParam Integer theatreId) {
        List<Schedule> schedules = scheduleRepository.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");

        List<Schedule> tempSchedules = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ArrayList<Showings> tempShowings = new ArrayList<>();
            List<Showings> showings = schedule.getShowings();
            for (Showings s : showings) {
                if (s.getTheatre().getTheatreId().equals(theatreId)) {
                    tempShowings.add(s);
                }
            }
            schedule.setShowings(tempShowings);
            tempSchedules.add(schedule);
        }
        int startSize = schedules.size();
        List<Schedule> finalSchedules = new ArrayList<>();

        for (int i = 0; i < startSize; i++) {
            if (!schedules.get(i).getShowings().isEmpty()) {
                finalSchedules.add(tempSchedules.get(i));
            }
        }

        if (!finalSchedules.isEmpty()) {
            ResponseEntity<String> response;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString(finalSchedules), httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response;
        } else {
            ResponseEntity<String> schedulesNotFound = new ResponseEntity<>("{\"message:\":\"There are no schedules with that theatre\"}", httpHeaders, HttpStatus.NOT_FOUND);
            return schedulesNotFound;
        }
    }


    @GetMapping(value = "/api/schedules/searchByMovieTitle")
    public ResponseEntity<String> getAllSchedulesByTitle(@RequestParam String movieTitle) {
        List<Schedule> schedules = scheduleRepository.findAll();        // Add all schedules to the list.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
//        ArrayList<Showings> tempShowings = new ArrayList<>();
        List<Schedule> tempSchedules = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ArrayList<Showings> tempShowings = new ArrayList<>();
            List<Showings> showings = schedule.getShowings();
            for (Showings s : showings) {
                if (s.getMovie().getTitle().equals(movieTitle)) {
                    tempShowings.add(s);
                }
            }
            schedule.setShowings(tempShowings);
            tempSchedules.add(schedule);

        }

        int startSize = schedules.size();
        List<Schedule> finalSchedules = new ArrayList<>();

        for (int i = 0; i < startSize; i++) {
            if (!schedules.get(i).getShowings().isEmpty()) {
                finalSchedules.add(tempSchedules.get(i));
            }
        }

        if (!finalSchedules.isEmpty()) {
            SpartaMongoDbFinalProjectApplication.logger.info("match2");
            ResponseEntity<String> response;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString(finalSchedules), httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response;
        } else {
            ResponseEntity<String> schedulesNotFound = new ResponseEntity<>("{\"message:\":\"There are no schedules with that movie title\"}", httpHeaders, HttpStatus.NOT_FOUND);
            return schedulesNotFound;
        }
    }



    @GetMapping(value = "/api/schedules/searchByDate")
    public ResponseEntity<String> getAllSchedulesByDate(@RequestParam LocalDate day) {
        List<Schedule> schedules = scheduleRepository.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");

        List<Schedule> tempSchedules = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ArrayList<Showings> tempShowings = new ArrayList<>();
            List<Showings> showings = schedule.getShowings();
            for (Showings s : showings) {
                if (schedule.getDay().toLocalDate().equals(day)) {
                    tempShowings.add(s);
                }
            }
            schedule.setShowings(tempShowings);
            tempSchedules.add(schedule);

        }

        int startSize = schedules.size();
        List<Schedule> finalSchedules = new ArrayList<>();

        for (int i = 0; i < startSize; i++) {
            if (!schedules.get(i).getShowings().isEmpty()) {
                finalSchedules.add(tempSchedules.get(i));
            }
        }


        if (!finalSchedules.isEmpty()) {
            ResponseEntity<String> response;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString(finalSchedules), httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response;
        } else {
            ResponseEntity<String> schedulesNotFound = new ResponseEntity<>("{\"message:\":\"There are no schedules on that date\"}", httpHeaders, HttpStatus.NOT_FOUND);
            return schedulesNotFound;
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "api/schedules/delete/{id}")
    public ResponseEntity<String> deleteScheduleById(@PathVariable String id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");

        if (schedule.isPresent()) {
            ResponseEntity<String> response = null;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString("Schedule has been deleted"), httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            scheduleRepository.deleteById(id);
            return response;
        }
        ResponseEntity<String> moviesNotFoundException = new ResponseEntity<>("A Schedule with that ID doesn't exist", httpHeaders, HttpStatus.NOT_FOUND);
        return moviesNotFoundException;
    }

    // Creating a schedule
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/api/schedules/create")
    public ResponseEntity<String> CreateSchedule(@RequestParam String movieId,
                                                 @RequestParam Integer theatreId,
                                                 @RequestParam LocalDateTime startTime,
                                                 @RequestParam LocalDateTime day) {

        Showings showings = new Showings();
        showings.setMovie(movieRepository.findById(movieId).get());
        showings.setTheatre(theatreRepository.findTheatreByTheatreId(theatreId).get(0));
        showings.setStart_time(startTime);
        ArrayList<Showings> showingsList = new ArrayList<>();
        showingsList.add(showings);

        Schedule schedule = new Schedule();
        schedule.setDay(day);
        schedule.setShowings(showingsList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        scheduleRepository.save(schedule);
        try {
            return new ResponseEntity<>(mapper.writeValueAsString("New Schedule has been added"), httpHeaders, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/api/schedules/edit/{scheduleId}")
    public ResponseEntity<String> editSchedule(@PathVariable String scheduleId,
                                               @RequestParam Integer chosenShowing,
                                               @RequestParam LocalDateTime day,
                                               @RequestParam String movieId,
                                               @RequestParam Integer theatreId,
                                               @RequestParam LocalDateTime startTime) {

        Schedule schedule = scheduleRepository.findScheduleById(scheduleId);
        schedule.setDay(day);
        schedule.getShowings().get(chosenShowing).setMovie(movieRepository.findMoviesById(movieId));
        schedule.getShowings().get(chosenShowing).setTheatre(theatreRepository.findTheatreByTheatreId(theatreId).get(0));
        schedule.getShowings().get(chosenShowing).setStart_time(startTime);
        scheduleRepository.save(schedule);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        try {
            return new ResponseEntity<>(mapper.writeValueAsString("Schedule has been updated"), httpHeaders, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}



