package tasks.familyhub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tasks.familyhub.entity.UserBackGround;
import tasks.familyhub.entity.UserIcon;

@Repository
public interface BackGroundRepository extends CrudRepository<UserBackGround, String> {
}
