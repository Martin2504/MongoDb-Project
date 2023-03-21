package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.model.entities.User;
import com.sparta.spartamongodbfinalproject.model.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserRestController {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;


    @Autowired
    public UserRestController(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }



//    @GetMapping("/api/user")
//    public ResponseEntity<List<User>> getUsersByName(@RequestParam("name") String name) {
//        List<User> users = userRepository.findByName(name);
//        if (!users.isEmpty()) {
//            return ResponseEntity.ok(users);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(users);
//        }
//    }

    // Read user details when given a name
    @GetMapping("/api/user")
    public ResponseEntity<String> getUsersByName(@RequestParam("name") String name) {
        List<User> users = userRepository.findByName(name);
        try {
            String json = objectMapper.writeValueAsString(users);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            if (!users.isEmpty()) {
                return ResponseEntity.ok().headers(httpHeaders).body(json);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(json);
            }
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update User by inputting a name
    @PatchMapping("api/user/{name}")
    public ResponseEntity<String> updateUserByName(@PathVariable("name") String name,
                                                   @RequestParam String email,
                                                   @RequestParam String password) {
        List<User> users = userRepository.findUsersByName(name);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        User user = users.get(0);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return ResponseEntity.ok(String.format("User %s updated with email %s and password %s", name, email, password));
    }

    //Delete All Users
    @DeleteMapping("api/user/{name}")
    public ResponseEntity<String> deleteAllUsersByName(@PathVariable("name") String name){
       // List<User> user = userRepository.findUsersByName(name);
        userRepository.deleteAll();
        return ResponseEntity.ok("All details deleted");
    }

    //Delete By Name/id
    @DeleteMapping("api/user/{id}/{name}")
    public ResponseEntity<String> deleteUserByName(@PathVariable ObjectId id,
                                                   @PathVariable("name") String name,
                                                   @RequestParam String email,
                                                   @RequestParam String password) {

        User user = new User();
        user.setId(String.valueOf(id));
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.deleteById(id);
        userRepository.deleteUsersByName(name);
        return ResponseEntity.ok("User details deleted");
    }

    //Create a user by passing a new id and other details
    @PostMapping ("api/user/{id}")
    public ResponseEntity<String> createComment(@PathVariable String id,
                                                @RequestParam String name,
                                                @RequestParam String email,
                                                @RequestParam String password) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return ResponseEntity.ok("New User has been created");
    }

}
