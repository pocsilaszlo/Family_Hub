package tasks.familyhub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tasks.familyhub.entity.UserImage;

import java.util.List;

@Repository
public interface ImageRepostirory extends CrudRepository<UserImage, String> {
    List<UserImage> findAllByUserIdAndCategory(String id, String category);
    List<UserImage> findAllByUserId(String id);
}
