package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.model.entities.Theatre;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Address;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Geo;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Location;
import com.sparta.spartamongodbfinalproject.model.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TheatersRestController {

    private final TheatreRepository theatreRepository;
    private final ObjectMapper mapper;

    @Autowired
    public TheatersRestController(TheatreRepository theatreRepository, ObjectMapper mapper) {
        this.theatreRepository = theatreRepository;
        this.mapper = mapper;
    }

    @GetMapping(value = "/theatres")
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @GetMapping(value = "/theatres/theatreId/{theatreId}")
    public ResponseEntity<String> getTheatreById(@PathVariable Integer theatreId) {
        Optional<Theatre> returnedTheatre = theatreRepository.findTheatreByTheatreId(theatreId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (returnedTheatre.isPresent()) {
            ResponseEntity<String> response = null;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString(returnedTheatre.get()), httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response;
        }

        ResponseEntity<String> theatreNotFoundResponse = new ResponseEntity<>("{\"message\";\"A theatre with that ID doesn't exist\"}", httpHeaders, HttpStatus.OK);
        return theatreNotFoundResponse;
    }

    @GetMapping(value = "/theatres/city/{city}")
    public ResponseEntity<String> getTheatreByCity(@PathVariable String city) {
        Optional<Theatre> returnedTheatre = theatreRepository.findTheatresByCity(city);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (returnedTheatre.isPresent()) {
            ResponseEntity<String> response = null;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString(returnedTheatre.get()), httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response;
        }

        ResponseEntity<String> theatreNotFoundResponse = new ResponseEntity<>("{\"message\";\"There are no theatres in that city\"}", httpHeaders, HttpStatus.OK);
        return theatreNotFoundResponse;
    }


    @PostMapping(value = "/theatres/create/")
    public ResponseEntity<String> createTheatre(@RequestParam Integer theatreId,
                                                @RequestParam String street1,
                                                @RequestParam String city,
                                                @RequestParam String state,
                                                @RequestParam String zipcode,
                                                @RequestParam Double co1,
                                                @RequestParam Double co2,
                                                @RequestParam String type) {
        HttpHeaders httpHeaders = new HttpHeaders();
        List<Double> coordinatesList = new ArrayList<>();
        coordinatesList.add(co1);
        coordinatesList.add(co2);
        Theatre theatre = new Theatre();
        Address address = new Address(street1, city, state, zipcode);
        address.setStreet1(street1);
        address.setCity(city);
        address.setState(state);
        address.setZipcode(zipcode);
        Geo geo = new Geo();
        geo.setType(type);
        geo.setCoordinates(coordinatesList);
        Location location = new Location(address, geo);
        theatre.setTheatreId(theatreId);
        theatre.setLocation(location);

        theatreRepository.save(theatre);

        Optional<Theatre> returnedTheatre = theatreRepository.findTheatreByTheatreId(theatreId);
        if (returnedTheatre.isPresent()) {
            return ResponseEntity.ok("New theatre has been created");
        }

        ResponseEntity<String> failedToCreateNewTheatre = new ResponseEntity<>("Failed to create new theatre", httpHeaders, HttpStatus.BAD_REQUEST);
        return failedToCreateNewTheatre;


    }



    @DeleteMapping("/theatres/delete/{theatreId}")
    public ResponseEntity<String> deleteTheatreById(@PathVariable Integer theatreId){
        Optional<Theatre> foundTheatre = theatreRepository.findTheatreByTheatreId(theatreId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(foundTheatre.isPresent()){
            ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"message\";\"That theatre has been deleted\"}", httpHeaders, HttpStatus.OK);
            theatreRepository.deleteTheatreByTheatreId(theatreId);
            return responseEntity;
        }
        else {
            ResponseEntity<String> noTheatreToDelete = new ResponseEntity<>("{\"message\";\"That theatre doesn't exist\"}", httpHeaders, HttpStatus.NOT_FOUND);
            return noTheatreToDelete;
        }
    }


    @PatchMapping("/theatres/edit/{theatreId}")
    public ResponseEntity<String> updateComment(
                                    @PathVariable Integer theatreId,
                                    @RequestParam String street1,
                                    @RequestParam String city,
                                    @RequestParam String state,
                                    @RequestParam String zipcode,
                                    @RequestParam Double co1,
                                    @RequestParam Double co2) {

        Optional<Theatre> theatre = theatreRepository.findTheatreByTheatreId(theatreId);
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(co1);
        coordinates.add(co2);
        theatre.get().getLocation().getAddress().setStreet1(street1);
        theatre.get().getLocation().getAddress().setCity(city);
        theatre.get().getLocation().getAddress().setState(state);
        theatre.get().getLocation().getAddress().setZipcode(zipcode);
        theatre.get().getLocation().getGeo().setCoordinates(coordinates);
        theatreRepository.save(theatre.get());

        return ResponseEntity.ok("Theatre details have been updated");

    }


}
