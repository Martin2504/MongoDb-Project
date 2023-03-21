package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;
import com.sparta.spartamongodbfinalproject.model.entities.Users;
import com.sparta.spartamongodbfinalproject.model.repositories.UsersRepositories;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersRestController {

    private final UsersRepositories usersRepositories;

    @Autowired
    public UsersRestController(UsersRepositories usersRepositories) {
        this.usersRepositories = usersRepositories;
    }
    

    @GetMapping("api/users")
    public ResponseEntity<String> getListOfAllUsers(@PathVariable ("id") ObjectId id) {

        List<Users> users = usersRepositories.findAll();
        return users;
    }


    //Read
    @GetMapping("api/users/{id}")
    public ResponseEntity<String> getListOfUsersById(@PathVariable("id") ObjectId id) {
        Optional<Users> optionalUsers = usersRepositories.findById(id);
        if(optionalUsers.isPresent()) {
            return (ResponseEntity<String>) this.usersRepositories.findUsersByName(String.valueOf(optionalUsers.get()));
        }
        else {
            return optionalUsers.map(value -> ResponseEntity.ok(value.getEmail())).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found"));
        }
    }

    //Update
    @PatchMapping(value = "api/users/{name}")
    public ResponseEntity<String> updateUserByName(@PathVariable("name") String name,
                                                   @RequestParam String email,
                                                   @RequestParam String password) {
        List<Users> users = usersRepositories.findUsersByName(name);
        users.get(Integer.parseInt(email)).setEmail(email);
        users.get(Integer.parseInt(password)).setPassword(password);
        usersRepositories.save(users.get(Integer.parseInt(email)));
        usersRepositories.save(users.get(Integer.parseInt(password)));
        return ResponseEntity.ok("User: " + users.get(Integer.parseInt(email)).getEmail() + "Email Updated" + users.get(Integer.parseInt(password)).getPassword() + "Password Updated");
    }


    //Delete All
    @DeleteMapping("api/users/{name}")
    public ResponseEntity<String> deleteAllUsersByName(@PathVariable("name") String name){
        List<Users> users = usersRepositories.findUsersByName(name);
        usersRepositories.deleteAll();
        return ResponseEntity.ok("All details deleted");
    }

    //Delete By Name
    @DeleteMapping("api/users/{name}")
    public ResponseEntity<String> deleteUserByName(@PathVariable("name") String name,
                                                   @RequestParam String email,
                                                   String password) {
        List<Users> users = usersRepositories.findUsersByName(name);
        usersRepositories.deleteUsersByName(email);
        usersRepositories.deleteUsersByName(password);
        return ResponseEntity.ok("User details deleted");
    }

    //Create
    @PostMapping("api/users/{id}")
    public ResponseEntity<String> createComment(@PathVariable ObjectId id,
                                                @RequestParam String name,
                                                @RequestParam String email,
                                                @RequestParam String password) {
        Users users = new Users();
        users.setId(id);
        users.setName(name);
        users.setEmail(email);
        users.setPassword(password);
        usersRepositories.save(users);
        return ResponseEntity.ok("New User has been created");
    }

}
