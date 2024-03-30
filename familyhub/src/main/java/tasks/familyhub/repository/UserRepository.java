package tasks.familyhub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tasks.familyhub.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
