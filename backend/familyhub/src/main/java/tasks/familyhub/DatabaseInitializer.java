package tasks.familyhub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tasks.familyhub.entity.App;
import tasks.familyhub.service.AppService;

import java.util.HashMap;
import java.util.Map;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final HashMap<String, String> apps = new HashMap<>();
    private final  AppService appService;
    @Autowired
    public DatabaseInitializer(AppService appService) {
        this.appService = appService;
    }

    @Override
    public void run(String... args) {


        apps.putAll(Map.of(
                "Facebook", "Közösségi média",
                "Instagram", "Közösségi média",
                "YouTube", "Videómegosztás",
                "Netflix", "Film- és sorozatstreaming",
                "Spotify", "Zenehallgatás",
                "Google Maps", "Navigáció",
                "Gmail", "E-mail",
                "WhatsApp", "Üzenetküldés",
                "Uber", "Taxirendelés",
                "Google Chrome", "Webböngésző"
        ));

        for (Map.Entry<String, String> app : apps.entrySet()) {
            appService.addApp(new App(app.getKey(), app.getValue()));
        }
        System.out.println("Database initialized!");
    }
}