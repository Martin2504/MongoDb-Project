package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;

import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class MoviesRestControllerTest {
@Autowired
    MockMvc mockMvc;
@Autowired
    MovieRepository movieRepository;
@Test
    @DisplayName("Check the default movies returns ok")
    void checkMoviesReturnsOk(){
    try {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }catch (Exception e) {
        e.printStackTrace();
    }
    }
    @Test
    @DisplayName("Check the movie by id returns ok even if the movie was not found")
    void checkMovieByIdReturnsOk(){
    try {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movie/6419a1e9821423636388b9d"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }catch (Exception e) {
        e.printStackTrace();
    }
    }    @Test
    @DisplayName("edit the movie by id returns ok even if the movie was not found")
    void editMovieByIdReturnsOk(){
    try {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movie/edit/6419a1e9821423636388b9d"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }catch (Exception e) {
        e.printStackTrace();
    }
    }
    @Test
    @DisplayName("Delete the movie by id returns ok even if the movie was not found")
    void deleteMovieByIdReturnsOk(){
    try {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movie/delete/6419a1e9821423636388b9d"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }catch (Exception e) {
        e.printStackTrace();
    }
    }
    @Test
    @DisplayName("Create the movie by id returns ok even if the movie was not found")
    void createMovieByIdReturnsOk(){
    try {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/movie/create"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }catch (Exception e) {
        e.printStackTrace();
    }
    }
    @Test
    @DisplayName("Check the movie by id returns ok even if the movie was not found")
    void checkMovieByIdReturnsMovieType(){
    try {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/movie/6419a1e9821423636388b9d"))
                .andReturn();
        String content = result.getRequest().getContentType();

    }catch (Exception e) {
        e.printStackTrace();
    }
    }
}
