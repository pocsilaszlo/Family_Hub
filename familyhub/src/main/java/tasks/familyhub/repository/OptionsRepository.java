package tasks.familyhub.repository;

import org.springframework.data.repository.CrudRepository;
import tasks.familyhub.entity.User;
import tasks.familyhub.entity.UserIcon;
import tasks.familyhub.entity.UserOptions;

public interface OptionsRepository extends CrudRepository<UserOptions, String> {


}
