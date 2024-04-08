package tasks.familyhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasks.familyhub.entity.App;
import tasks.familyhub.entity.User;
import tasks.familyhub.service.AppService;
import tasks.familyhub.service.UserService;

import java.util.List;

@RestController
public class AppController {

    private final AppService appService;

    private final UserService userService;

    @Autowired
    public AppController(AppService appService, UserService userService) {
        this.appService = appService;
        this.userService = userService;
    }

    @GetMapping("/apps")
    public ResponseEntity<List<App>> getApps() {
        return new ResponseEntity<>(appService.getApps(), HttpStatus.OK);
    }

    @GetMapping("/apps/user/{id}")
    public ResponseEntity<List<App>> getAppsByUser(@PathVariable String id) {
        return new ResponseEntity<>(appService.getAppsByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/apps/store/user/{id}")
    public ResponseEntity<List<App>> getAppsExceptByUser(@PathVariable String id) {
        return new ResponseEntity<>(appService.getAppsExceptByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/apps/{name}")
    public ResponseEntity<App> getAppsByName(@PathVariable String name) {
        return new ResponseEntity<>(appService.getAppByName(name), HttpStatus.OK);
    }


    @PostMapping("/apps/{appId}/user/{userId}")
    public ResponseEntity<?> addApp(@PathVariable String appId, @PathVariable String userId) {
        User user = userService.getUser(userId);
        App app = appService.getApp(appId);
        appService.addApp(user, app);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/apps")
    public ResponseEntity<App> addAppToStore(@RequestBody App app) {
        return new ResponseEntity<>(appService.addApp(app), HttpStatus.CREATED);
    }
}
