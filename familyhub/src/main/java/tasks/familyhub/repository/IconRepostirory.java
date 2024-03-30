package tasks.familyhub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tasks.familyhub.entity.User;
import tasks.familyhub.entity.UserIcon;

@Repository
public interface IconRepostirory extends CrudRepository<UserIcon, String> {
}
