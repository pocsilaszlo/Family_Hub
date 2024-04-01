package tasks.familyhub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tasks.familyhub.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User getById(String id);

}
