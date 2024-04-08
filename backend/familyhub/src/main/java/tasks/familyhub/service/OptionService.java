package tasks.familyhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasks.familyhub.entity.UserOptions;
import tasks.familyhub.repository.OptionsRepository;

import java.util.List;

@Service
public class OptionService {

    private final OptionsRepository optionsRepository;

    @Autowired
    public OptionService(OptionsRepository optionsRepository) {
        this.optionsRepository = optionsRepository;
    }

    public List<UserOptions> getOptions() {
        return  optionsRepository.findAll();
    }

    public UserOptions getOptionsByUserId(String id) {
        return optionsRepository.getByUserId(id);
    }

    public void addOptions(UserOptions options) {
        optionsRepository.save(options);
    }

    public void deleteOptions (UserOptions options) {
        optionsRepository.delete(options);
    }
}
