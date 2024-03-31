package tasks.familyhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tasks.familyhub.entity.User;
import tasks.familyhub.repository.OptionsRepository;
import tasks.familyhub.repository.UserRepository;
import tasks.familyhub.service.UserService;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class UserController {


    UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users() {
        return userService.getUsers().toString();
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable String id) {
        return userService.getUser(id).toString();
    }

    @PostMapping("/users")
    public User addUser() {

        return userService.addUser();
    }

}
