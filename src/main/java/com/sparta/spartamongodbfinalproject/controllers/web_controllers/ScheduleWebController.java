package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.entities.Schedule;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Location;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Showings;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.ScheduleRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.TheatreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
            upperDate = date.plusDays(365);
        }
        date = date.minusDays(1);


        List<Schedule> scheduleList = scheduleRepository.findAll();



        if(!title.equals("all")){
            List<Schedule> tempScheduleList = new ArrayList<>();
            for(Schedule schedule : scheduleList){
                ArrayList<Showings> tempShowings = new ArrayList<>();
                for (Showings showings : schedule.getShowings()) {
                    if (showings.getMovie().getTitle().toLowerCase().contains(title.toLowerCase())) {
                        tempShowings.add(showings);
                    }
                }
                schedule.setShowings(tempShowings);
                tempScheduleList.add(schedule);
            }
            scheduleList = tempScheduleList;

        }
        scheduleList.removeAll(Collections.singleton(null));


        if (!city.equals("all")) {
            List<Schedule> tempScheduleList = new ArrayList<>();
            for (Schedule schedule : scheduleList) {
                ArrayList<Showings> tempShowings = new ArrayList<>();
                for (Showings showings : schedule.getShowings()) {
                    String location = showings.getTheatre().getLocation().getAddress().getCity();
                    if (location.toLowerCase().contains(city.toLowerCase())) {
                        tempShowings.add(showings);
                    }
                }
                schedule.setShowings(tempShowings);
                tempScheduleList.add(schedule);
            }
            scheduleList = tempScheduleList;
        }




        List<Schedule> tempScheduleList = new ArrayList<>();
        for(Schedule schedule : scheduleList){
                if(date.isBefore(ChronoLocalDate.from(schedule.getDay())) && upperDate.isAfter(ChronoLocalDate.from(schedule.getDay()))){
                    tempScheduleList.add(schedule);
                }
        }
        Set<Schedule> tempScheduleSet = new HashSet<>(tempScheduleList);
        scheduleList = tempScheduleSet.stream().toList();
        return getScheduleDates(model, scheduleList);
    }

    private String getScheduleDates(Model model, List<Schedule> scheduleList) {
        model.addAttribute("todaysDate", LocalDate.now());
        Set<Integer> days = new HashSet<>();
        List<String> times = new ArrayList<>();
        List<List<String>> timesByDay = new ArrayList<>();
        Set<LocalDate> dates = new HashSet<>();

        for(Schedule schedule : scheduleList){
            for (Showings showings : schedule.getShowings()){
                showings.setStartTimeString(showings.getStart_time().format(DateTimeFormatter.ofPattern("HH:mm")));
            }
            days.add(schedule.getDay().getDayOfYear());
            dates.add(schedule.getDay().toLocalDate());
            timesByDay.add(times);
        }
        LocalDate[] dateList = dates.toArray(new LocalDate[0]);
        Arrays.sort(dateList);
        Integer count = 0;
        model.addAttribute("schedules", scheduleList);
        model.addAttribute("days", days);
        model.addAttribute("times", timesByDay);
        model.addAttribute("dates", dateList);
        model.addAttribute("count", count);
        return "schedule/schedule-search-results";
    }
    @GetMapping("/schedules/edit/{id}/{showingId}")
    private String getScheduletoEdit(@PathVariable String id, Model model,@PathVariable int showingId) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        String movieId = schedule.getShowings().get(showingId).getMovie().getId();
        Integer theaterId = schedule.getShowings().get(showingId).getTheatre().getTheatreId();
        LocalDateTime start_time = schedule.getShowings().get(showingId).getStart_time();
        LocalTime start_timeString = start_time.toLocalTime();
        model.addAttribute("schedule",schedule);
        model.addAttribute("originalMovieId", movieId);
        model.addAttribute("originalTheaterId", theaterId);
        model.addAttribute("originalStartTime",start_timeString);
        model.addAttribute("originalDate", schedule.getDay().toLocalDate());
        model.addAttribute("successful", "");
        return "schedule/schedule-edit";
    }
    @GetMapping("/edit/schedule")
    private String editSchedule(Model model, @RequestParam LocalDate day, @RequestParam String movieId, @RequestParam String theaterId, @RequestParam LocalTime start_time, @RequestParam String scheduleId){
        System.out.println(day);
        System.out.println(movieId);
        System.out.println(theaterId);
        System.out.println(start_time);
        System.out.println(scheduleId);
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);
        model.addAttribute("schedule",schedule);
        model.addAttribute("originalMovieId", movieId);
        model.addAttribute("originalTheaterId", theaterId);
        model.addAttribute("originalStartTime",start_time);
        model.addAttribute("originalDate", day);
        model.addAttribute("successful", "Update Successful");
        return "schedule/schedule-edit";
    }
    @GetMapping("/delete/schedule/{id}")
    private String deleteEntireSchedule(@PathVariable String id){
        scheduleRepository.deleteById(id);
        return "schedule/delete-success";
    }



}
