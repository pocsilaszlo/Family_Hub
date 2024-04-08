package tasks.familyhub.service;

import tasks.familyhub.entity.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasks.familyhub.entity.User;
import tasks.familyhub.repository.AppRepository;

import java.util.List;

@Service
public class AppService {

    private final AppRepository appRepository;

    @Autowired
    public AppService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public List<App> getApps() {
        return appRepository.findAll();
    }

    public App getApp(String id) {return appRepository.getById(id);};

    public List<App> getAppsByUserId(String id) {
        return appRepository.findAllByUsersId(id);
    }

    public List<App> getAppsExceptByUserId(String id) {
        List<App> apps = appRepository.findAllByUsersId(id);
        List<App> allApps = appRepository.findAll();
        return allApps.stream().filter( x -> !apps.contains(x)).toList();
    }

    public App getAppByName(String name) {
        return appRepository.getByName(name);
    }
    public void addApp(User user, App app) {
        user.addApps(app);
        app.addUser(user);
        appRepository.save(app);
    }

    public App addApp(App app) {
        return  appRepository.save(app);
    }
}
