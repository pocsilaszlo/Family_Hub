package tasks.familyhub.repository;

import org.hibernate.annotations.processing.Find;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tasks.familyhub.entity.User;
import tasks.familyhub.entity.UserIcon;
import tasks.familyhub.entity.UserOptions;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface OptionsRepository extends CrudRepository<UserOptions, String> {

    List<UserOptions> findAll();
    UserOptions getByUserId(String string);
}
