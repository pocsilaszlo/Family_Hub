package tasks.familyhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasks.familyhub.entity.User;
import tasks.familyhub.entity.UserOptions;
import tasks.familyhub.repository.OptionsRepository;
import tasks.familyhub.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private OptionsRepository optionsRepository;

    @Autowired
    public void setOptionsRepository(OptionsRepository optionsRepository) {
        this.optionsRepository = optionsRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.getById(id);
    }
    public User addUser() {
        User user = new User("Laszlo", "alma", "parent");
        UserOptions options = new UserOptions();
        //user.setOptions(options);
        options.setUser(user);
        optionsRepository.save(options);
        return userRepository.save(user);
    }
}
