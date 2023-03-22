package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;

import com.sparta.spartamongodbfinalproject.model.repositories.CommentRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommentsRestControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CommentRepository commentRepository;

    @Test
    void createComment() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/movie/6419a1e9821423636388b9d"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getCommentById() {
    }

    @Test
    void updateComment() {
    }


    @Test
    void deleteComment() {
    }
}