package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;
import com.sparta.spartamongodbfinalproject.model.entities.User;
import com.sparta.spartamongodbfinalproject.model.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    private final UserRepository userRepository;


    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    @GetMapping("api/user")
//    public ResponseEntity<String> getListOfAllUsers(@PathVariable ("id") ObjectId id) {
//
//        List<User> user = userRepository.findAll();
//
//    }


    //Read
    @GetMapping("api/user/{id}")
    public ResponseEntity<String> getListOfUsersById(@PathVariable("id") ObjectId id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return (ResponseEntity<String>) this.userRepository.findUsersByName(String.valueOf(optionalUser.get()));
        }
        else {
            return optionalUser.map(value -> ResponseEntity.ok(value.getEmail())).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found"));
        }
    }

    //Update
    @PatchMapping(value = "api/user/{name}")
    public ResponseEntity<String> updateUserByName(@PathVariable("name") String name,
                                                   @RequestParam String email,
                                                   @RequestParam String password) {
        List<User> user = userRepository.findUsersByName(name);
        user.get(Integer.parseInt(email)).setEmail(email);
        user.get(Integer.parseInt(password)).setPassword(password);
        userRepository.save(user.get(Integer.parseInt(email)));
        userRepository.save(user.get(Integer.parseInt(password)));
        return ResponseEntity.ok("User: " + user.get(Integer.parseInt(email)).getEmail() + "Email Updated" + user.get(Integer.parseInt(password)).getPassword() + "Password Updated");
    }


    //Delete All
    @DeleteMapping("api/user/{name}")
    public ResponseEntity<String> deleteAllUsersByName(@PathVariable("name") String name){
        List<User> user = userRepository.findUsersByName(name);
        userRepository.deleteAll();
        return ResponseEntity.ok("All details deleted");
    }

    //Delete By Name
    @DeleteMapping("api/user/{name}")
    public ResponseEntity<String> deleteUserByName(@PathVariable("name") String name,
                                                   @RequestParam String email,
                                                   String password) {
        List<User> users = userRepository.findUsersByName(name);
        userRepository.deleteUsersByName(email);
        userRepository.deleteUsersByName(password);
        return ResponseEntity.ok("User details deleted");
    }

    //Create
    @PostMapping("api/user/{id}")
    public ResponseEntity<String> createComment(@PathVariable ObjectId id,
                                                @RequestParam String name,
                                                @RequestParam String email,
                                                @RequestParam String password) {
        User user = new User();
        user.setId(String.valueOf(id));
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return ResponseEntity.ok("New User has been created");
    }

}
