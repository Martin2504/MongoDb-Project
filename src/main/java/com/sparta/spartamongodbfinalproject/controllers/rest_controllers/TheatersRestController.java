package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.model.entities.Theatre;
import com.sparta.spartamongodbfinalproject.model.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TheatersRestController {

    private TheatreRepository theatreRepository;
    private ObjectMapper mapper;

    @Autowired
    public TheatersRestController(TheatreRepository theatreRepository, ObjectMapper mapper){
        this.theatreRepository = theatreRepository;
        this.mapper = mapper;
    }

    @GetMapping(value = "/theatres")
    public List<Theatre> getAllTheatres(){
        return theatreRepository.findAll();
    }

    @GetMapping(value = "/theatres/theatreId/{theatreId}")
    public ResponseEntity<String> getTheatreById(@PathVariable Integer theatreId){
        Optional<Theatre> returnedTheatre = theatreRepository.findTheatreByTheatreId(theatreId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(returnedTheatre.isPresent()){
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
    public ResponseEntity<String> getTheatreByCity(@PathVariable String city ){
        Optional<Theatre> returnedTheatre = theatreRepository.findTheatresByCity(city);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(returnedTheatre.isPresent()){
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



}
