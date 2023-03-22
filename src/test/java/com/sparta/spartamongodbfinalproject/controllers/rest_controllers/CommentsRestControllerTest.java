package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

class CommentsRestControllerTest {

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