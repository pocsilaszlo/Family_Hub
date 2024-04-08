package tasks.familyhub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tasks.familyhub.entity.UserOptions;

import java.util.List;

@Repository
public interface OptionsRepository extends CrudRepository<UserOptions, String> {

    List<UserOptions> findAll();
    UserOptions getByUserId(String string);
}
