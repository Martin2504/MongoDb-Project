package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.model.entities.Theatre;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Address;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Geo;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Location;
import com.sparta.spartamongodbfinalproject.model.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class TheatresWebController {
    private final TheatreRepository theatersRepository;

    @Autowired
    public TheatresWebController(TheatreRepository theatersRepository) {
        this.theatersRepository = theatersRepository;
    }

    // Create a new Theater.
    @GetMapping("/createTheatre")
    public String createTheatre(Model model) {
        return "theatresPages/theatre-create-form";
    }

    // Receive Theater object to create from a form.
    @PostMapping("/saveTheatre")
    public String saveTheatre(@ModelAttribute("theaterToCreate") Theatre theatres, @ModelAttribute("Location") Location location,
                              @ModelAttribute("Address") Address address, @ModelAttribute("Deo") Geo geo, String coordinatesString) {
//        SpartaMongoDbFinalProjectApplication.logger.info(theatres.toString());
        location.setAddress(address);
        String[] single = coordinatesString.split(",");
        ArrayList<Double> coordinates = new ArrayList<>();
        for (String s : single) {
            coordinates.add(Double.parseDouble(s));
        }
        geo.setCoordinates(coordinates);
        location.setGeo(geo);
        theatres.setLocation(location);
        theatersRepository.save(theatres);
        return "theatresPages/success";
    }

    // Request to search for a theater by id.
    @GetMapping("/searchForTheatre")
    public String getTheatre() { return "theatresPages/theatres-find-form"; }

    // Search for a theater by id.
    @PostMapping("findTheatreById")
    public String findTheatreById(@ModelAttribute("theaterToFind") Theatre theaters, Integer theaterId, Model model) {
        model.addAttribute("theatreToFind", theatersRepository.findTheatreByTheatreId(theaterId));
        return "theatresPages/theatreById";
    }

    // Request to edit a theatre.
    @GetMapping("/theatre/edit/{id}")
    public String getTheatreToEdit(@PathVariable String id, Model model) {
        Theatre theatre = theatersRepository.findById(id).orElse(null);
        model.addAttribute("theatreToEdit", theatre);
        return "theatresPages/theatre-edit-form";
    }

    // Receives a Theatre object to edit from a form.
    @PostMapping("updateTheatre")
    public String updateTheatre(@ModelAttribute("theatreToEdit") Theatre editedTheatre) {
        theatersRepository.save(editedTheatre);
        return "theatresPages/success";
    }

    // Delete a Theatre.

    // (Manually delete)
    @GetMapping("/theatre/manualDelete/{id}")
    public String deleteTheatreManual(@PathVariable String id) {
        theatersRepository.deleteById(id);
        return "theatresPages/success";
    }

    // Delete properly
    @GetMapping("theatre/delete/{id}")
    public String deleteTheatre(@PathVariable String id) {
        theatersRepository.deleteById(id);
        return "theatresPages/success";
    }

}
