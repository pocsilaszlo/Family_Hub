package tasks.familyhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasks.familyhub.dto.OptionsResponse;
import tasks.familyhub.entity.UserOptions;
import tasks.familyhub.service.OptionService;

import java.util.List;

@RestController
public class OptionsController {

    private final OptionService optionService;

    @Autowired
    public OptionsController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping("/options")
    public ResponseEntity<List<UserOptions>> getOptions() {
        return new ResponseEntity<>(optionService.getOptions(), HttpStatus.OK);
    }

    @PostMapping("/options/theme/{id}")
    public  ResponseEntity<?> setTheme(@PathVariable String id, @RequestBody Boolean isDark) {
        UserOptions options =  optionService.getOptionsByUserId(id);
        options.setDarkMode(isDark);
        optionService.addOptions(options);
        System.out.println(options.getDarkMode());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/options/{id}")
    public ResponseEntity<OptionsResponse> getOptionByUserId(@PathVariable String id) {
        UserOptions option = optionService.getOptionsByUserId(id);
        OptionsResponse response = new OptionsResponse(option.getId(), option.getIconId(), option.getBackGroundId(), option.getDarkMode());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/options/background/{userId}/{bgId}")
    public ResponseEntity<OptionsResponse> setBackground(@PathVariable String userId, @PathVariable String bgId) {
        UserOptions option = optionService.getOptionsByUserId(userId);
        option.setBackGroundId(bgId);
        optionService.addOptions(option);
        return new ResponseEntity<>(new OptionsResponse(option.getId(), option.getIconId(), option.getBackGroundId(), option.getDarkMode()), HttpStatus.OK);
    }

    @DeleteMapping("/options/background/{userId}")
    public ResponseEntity<OptionsResponse> deleteBackground(@PathVariable String userId) {
        UserOptions option = optionService.getOptionsByUserId(userId);
        option.setBackGroundId(null);
        optionService.addOptions(option);
        return new ResponseEntity<>(new OptionsResponse(option.getId(), option.getIconId(), option.getBackGroundId(), option.getDarkMode()), HttpStatus.OK);
    }

    @PostMapping("/options/icon/{userId}/{iconId}")
    public ResponseEntity<?> setIcon(@PathVariable String userId, @PathVariable String iconId) {
        UserOptions option = optionService.getOptionsByUserId(userId);
        option.setIconId(iconId);
        optionService.addOptions(option);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/options/icon/{userId}")
    public ResponseEntity<OptionsResponse> deleteIcon(@PathVariable String userId) {
        UserOptions option = optionService.getOptionsByUserId(userId);
        option.setIconId(null);
        optionService.addOptions(option);
        return new ResponseEntity<>(new OptionsResponse(option.getId(), option.getIconId(), option.getBackGroundId(), option.getDarkMode()), HttpStatus.OK);
    }
}
