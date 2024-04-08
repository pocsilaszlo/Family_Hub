package tasks.familyhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasks.familyhub.entity.App;
import tasks.familyhub.entity.Token;
import tasks.familyhub.entity.User;
import tasks.familyhub.entity.UserImage;
import tasks.familyhub.repository.*;

import java.util.List;

@Service
public class UserService{

    private final UserRepository userRepository;

    private final OptionsRepository optionsRepository;

    private final AppRepository appRepository;

    private final ImageRepostirory imageRepostirory;

    private final TokenRepository tokenRepository;


    @Autowired
    public UserService(UserRepository userRepository, OptionsRepository optionsRepository,
                       AppRepository appRepository, ImageRepostirory imageRepostirory, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.optionsRepository = optionsRepository;
        this.appRepository = appRepository;
        this.imageRepostirory = imageRepostirory;
        this.tokenRepository = tokenRepository;
    }

    public Boolean existUser(String id) { return userRepository.existsById(id); }

    public Boolean existByName(String id) { return userRepository.existsByEmail(id); }
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.getById(id);
    }

    public User putUser(User user) {
        return  userRepository.save(user);
    }

    public void deleteUser(String id) {
        User user = userRepository.getById(id);

        List<App> apps = appRepository.findAllByUsersId(id);
        for (App app : apps) {
            app.removeUser(user);
            appRepository.save(app);
        }

        List<UserImage> images = imageRepostirory.findAllByUserId(id);
        for (UserImage image : images) {
            imageRepostirory.delete(image);
        }

        List<Token> tokens = tokenRepository.findAllTokensByUser(id);
        for (Token token : tokens) {
            user.removeToken(token);
            tokenRepository.delete(token);
        }

        optionsRepository.delete((optionsRepository.getByUserId(id)));
        userRepository.delete(user);
    }


}
