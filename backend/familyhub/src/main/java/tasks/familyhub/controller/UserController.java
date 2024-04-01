package tasks.familyhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasks.familyhub.entity.App;
import tasks.familyhub.entity.User;
import tasks.familyhub.entity.UserOptions;
import tasks.familyhub.repository.OptionsRepository;
import tasks.familyhub.repository.UserRepository;
import tasks.familyhub.service.AppService;
import tasks.familyhub.service.OptionService;
import tasks.familyhub.service.UserService;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.ID;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@CrossOrigin
public class UserController {


    UserService userService;

    OptionService optionService;

    AppService appService;

    @Autowired
    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
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

    @GetMapping("/options")
    public ResponseEntity<List<UserOptions>> getOptions() {
        return new ResponseEntity<>(optionService.getOptions(), HttpStatus.OK);
    }

    @GetMapping("/options/{id}")
    public ResponseEntity<UserOptions> getOptionByUserId(@PathVariable String id) {
        return new ResponseEntity<>(optionService.getOptionsByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/apps")
    public ResponseEntity<List<App>> getApps() {
        return new ResponseEntity<>(appService.getApps(), HttpStatus.OK);
    }

    @GetMapping("/apps/{id}")
    public ResponseEntity<List<App>> getApps(@PathVariable String id) {
        return new ResponseEntity<>(appService.getAppsByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/apps/{id}")
    public ResponseEntity<App> addApp(@PathVariable String id, @RequestBody App app) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(appService.addApp(user), HttpStatus.CREATED);
    }
}
