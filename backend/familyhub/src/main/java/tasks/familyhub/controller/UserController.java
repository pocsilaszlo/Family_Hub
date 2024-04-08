package tasks.familyhub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasks.familyhub.dto.AuthenticationResponse;
import tasks.familyhub.dto.UserResponse;
import tasks.familyhub.entity.User;
import tasks.familyhub.service.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {


    private final UserService userService;

    private final SimulationService simulationService;
    private final OptionService optionService;

    private final AppService appService;

    private final AuthenticationService authService;


    @Autowired
    public UserController(UserService userService, SimulationService simulationService,OptionService optionService, AppService appService, AuthenticationService authService) {
        this.userService = userService;
        this.simulationService = simulationService;
        this.optionService = optionService;
        this.appService = appService;
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> users() {
        ArrayList<User> users = (ArrayList<User>) userService.getUsers();
        ArrayList<UserResponse> userResponse = new ArrayList<>();
        for (User user : users) {
            UserResponse userDetails = new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
            userResponse.add(userDetails);
        }
        return new ResponseEntity<>(userResponse , HttpStatus.OK
        );
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        User user = userService.getUser(id);
        UserResponse userDetails = new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.putUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/role/{id}")
    public ResponseEntity<String> gerRoleById (@PathVariable String id) {
        if (!userService.existUser(id))
            return new ResponseEntity<>(HttpStatus.OK);

        User user = userService.getUser(id);
        return new ResponseEntity<>(user.getRole(), HttpStatus.OK);
    }

    @PostMapping("/simulation")
    public ResponseEntity<?> simulate() {
        simulationService.simulate();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/token/{tokenId}/user/{userId}")
    public ResponseEntity<Boolean> searchToken(@PathVariable String tokenId, @PathVariable String userId) {
        if (userService.existUser(userId) == false) { return new ResponseEntity<>(false, HttpStatus.OK); }
        return new ResponseEntity<>(authService.checkToken(tokenId, userService.getUser(userId)), HttpStatus.OK);
    }
}
