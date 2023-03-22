package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.model.entities.Schedule;
import com.sparta.spartamongodbfinalproject.model.entities.Theatre;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.ScheduleRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value = "/api/schedules/{theatreId}")
    public Schedule getAllSchedulesByTheatreId(@PathVariable Integer theatreId) {
        return scheduleRepository.findScheduleById(theatreId);
    }




}
