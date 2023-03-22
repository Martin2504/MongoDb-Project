package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.model.entities.Schedule;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.ScheduleRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.TheatreRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class ScheduleWebController {

    private final ScheduleRepository scheduleRepository;
    private final TheatreRepository theatreRepository;
    private final MovieRepository movieRepository;

    public ScheduleWebController(ScheduleRepository scheduleRepository, TheatreRepository theatreRepository, MovieRepository movieRepository) {
        this.scheduleRepository = scheduleRepository;
        this.theatreRepository = theatreRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/schedules")
    public String getAllSchedules(Model model){
        List<Schedule> scheduleList = scheduleRepository.findAll();
        Set<Integer> days = new HashSet<>();
        List<Array> times = new List<Array>();
        for(Schedule schedule : scheduleList){
            for(LocalDateTime day : schedule.getStartTime()){
                days.add(day.getDayOfYear());
            }
        }
        for(Schedule schedule : scheduleList){
            for(LocalDateTime day : schedule.getStartTime()){
                times.add(day.getHour());
            }
        }
        model.addAttribute("schedules", scheduleList);
        return "schedule/schedule-search-results";
    }

}
