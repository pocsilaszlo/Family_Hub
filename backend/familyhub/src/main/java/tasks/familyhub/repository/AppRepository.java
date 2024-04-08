package tasks.familyhub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tasks.familyhub.entity.App;

import java.util.List;

@Repository
public interface AppRepository extends CrudRepository<App, String> {

    List<App> findAll();
    List<App> findAllByUsersId(String usersId);
    App getByName(String name);
    App getById(String id);
}
