package tasks.familyhub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tasks.familyhub.entity.App;

@Repository
public interface AppRepository extends CrudRepository<App, String> {

}
