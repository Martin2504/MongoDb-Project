package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.entities.Schedule;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Location;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.ScheduleRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.TheatreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/web")
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
        return getScheduleDates(model, scheduleList);
    }

    @GetMapping("/schedules/")
    public String getSchedulesByAllParameters(Model model, @RequestParam String city, @RequestParam String title, @RequestParam LocalDate date) {
        LocalDate upperDate = date.plusDays(7);
        SpartaMongoDbFinalProjectApplication.logger.info(title);
        if (city.equals("")) {
            city = "all";
        }
        if (title.strip().equals("")) {
            title = "all";
        }
        SpartaMongoDbFinalProjectApplication.logger.info(title);
        if (date.equals(LocalDate.now())) {
            upperDate = date.plusDays(30);
        }
        date = date.minusDays(1);
        List<Schedule> scheduleList = scheduleRepository.findAll();
        if(!title.equals("all")){
            List<Schedule> tempScheduleList = new ArrayList<>();
            for(Schedule schedule : scheduleList){
                if(schedule.getMovie().getTitle().toLowerCase().contains(title.toLowerCase())){
                    tempScheduleList.add(schedule);
                }
            }
            scheduleList = tempScheduleList;
        }
        scheduleList.removeAll(Collections.singleton(null));
        if (!city.equals("all")) {
            List<Schedule> tempScheduleList = new ArrayList<>();
            for (Schedule schedule : scheduleList) {
                Optional<String> location = schedule.getTheatre().getLocation().getAddress().getCity();
                if (city.toLowerCase().contains(location.toLowerCase())) {
                    tempScheduleList.add(schedule);
                }
            }
            scheduleList = tempScheduleList;
        }
        List<Schedule> tempScheduleList = new ArrayList<>();
        for(Schedule schedule : scheduleList){
            for(LocalDateTime currentScheduleDate : schedule.getStartTime()){
                if(date.isBefore(ChronoLocalDate.from(currentScheduleDate)) && upperDate.isAfter(ChronoLocalDate.from(currentScheduleDate))){
                    tempScheduleList.add(schedule);
                }
            }
        }
        Set<Schedule> tempScheduleSet = new HashSet<>(tempScheduleList);
        scheduleList = tempScheduleSet.stream().toList();
        return getScheduleDates(model, scheduleList);
    }

    private String getScheduleDates(Model model, List<Schedule> scheduleList) {
        model.addAttribute("todaysDate", LocalDate.now());
        Set<Integer> days = new HashSet<>();
        List<List<String>> times = new ArrayList<>();
        Set<LocalDate> dates = new HashSet<>();
        for(Schedule schedule : scheduleList){
            List<String> hours = new ArrayList<>();
            for(LocalDateTime day : schedule.getStartTime()){
                days.add(day.getDayOfYear());
                dates.add(day.toLocalDate());
                hours.add(day.format(DateTimeFormatter.ofPattern("HH:mm")));
            }
            times.add(hours);
        }
        LocalDate[] dateList = dates.toArray(new LocalDate[0]);
        Arrays.sort(dateList);
        model.addAttribute("schedules", scheduleList);
        model.addAttribute("days", days);
        model.addAttribute("times", times);
        model.addAttribute("dates", dateList);
        return "schedule/schedule-search-results";
    }

}
