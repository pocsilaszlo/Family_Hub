package tasks.familyhub.service;

import tasks.familyhub.entity.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasks.familyhub.entity.User;
import tasks.familyhub.repository.AppRepository;

import java.util.List;

@Service
public class AppService {

    private AppRepository appRepository;

    @Autowired
    public void setAppRepository(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public List<App> getApps() {
        return appRepository.findAll();
    }

    public List<App> getAppsByUserId(String id) {
        return appRepository.findAllByUsersId(id);
    }
    public App addApp(User user) {
        App cs = new App("Counter-Strike", "game");
        user.addApps(cs);
        cs.addUser(user);
        return  appRepository.save(cs);
    }
}
