package tasks.familyhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasks.familyhub.entity.User;
import tasks.familyhub.entity.UserOptions;
import tasks.familyhub.repository.OptionsRepository;

import java.util.List;

@Service
public class SimulationService {

    private final UserService userService;

    private final AuthenticationService authService;

    private final AppService appService;

    private final OptionsRepository optionsRepository;

    @Autowired
    public SimulationService(UserService userService,AuthenticationService authService ,AppService appService, OptionsRepository optionsRepository) {
        this.userService = userService;
        this.authService = authService;
        this.appService = appService;
        this.optionsRepository = optionsRepository;
    }

    public void simulate() {
        List<User> users = List.of(
                new User("Minta szülő 1", "szulo1@gmail.com", "password", "parent"),
                new User("Minta szülő 2", "szulo2@gmail.com", "password", "parent"),
                new User("Minta gyerek 1", "gyerek1@gmail.com", "password", "child"),
                new User("Minta gyerek 2", "gyerek2@gmail.com", "password", "child"),
                new User("Minta gyerek 3", "gyerek3@gmail.com", "password", "child")
        );

        for (User user : users) {
            authService.register(user);
        }

        users = userService.getUsers();

        for (int i = 0; i < users.size(); i++) {
            if (i % 2 == 0) {
                System.out.println(users.get(i).getId());
                UserOptions options = optionsRepository.getByUserId(users.get(i).getId());
                System.out.println(options.toString());
                options.setDarkMode(true);
                optionsRepository.save(options);
            }
        }

        appService.addApp(users.get(0), appService.getAppByName("Google Maps"));
        appService.addApp(users.get(0), appService.getAppByName("Gmail"));
        appService.addApp(users.get(0), appService.getAppByName("Google Chrome"));

        appService.addApp(users.get(1), appService.getAppByName("WhatsApp"));
        appService.addApp(users.get(1), appService.getAppByName("Google Chrome"));

        appService.addApp(users.get(2), appService.getAppByName("Facebook"));
        appService.addApp(users.get(2), appService.getAppByName("Gmail"));
        appService.addApp(users.get(2), appService.getAppByName("Instagram"));

        appService.addApp(users.get(3), appService.getAppByName("YouTube"));
        appService.addApp(users.get(3), appService.getAppByName("Uber"));
        appService.addApp(users.get(3), appService.getAppByName("Netflix"));
        appService.addApp(users.get(3), appService.getAppByName("Instagram"));

        appService.addApp(users.get(4), appService.getAppByName("Netflix"));
        appService.addApp(users.get(4), appService.getAppByName("Spotify"));
    }
}
