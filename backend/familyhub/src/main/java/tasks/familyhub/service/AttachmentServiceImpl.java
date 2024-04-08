package tasks.familyhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tasks.familyhub.entity.User;
import tasks.familyhub.entity.UserImage;
import tasks.familyhub.repository.ImageRepostirory;
import tasks.familyhub.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    private final ImageRepostirory attachmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public AttachmentServiceImpl(ImageRepostirory attachmentRepository, UserRepository userRepository) {
        this.attachmentRepository = attachmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserImage saveAttachment(MultipartFile file, String id, String category) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            UserImage attachment = new UserImage(fileName, file.getBytes(), file.getContentType(), category);

            User user = userRepository.getById(id);
            attachment.setUser(user);
            return attachmentRepository.save(attachment);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }
    @Override
    public UserImage getAttachment(String fileId) throws Exception {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }
    @Override
    public List<String> getAllIdByUser(String id, String category) {
        ArrayList<UserImage> images = (ArrayList<UserImage>) attachmentRepository.findAllByUserIdAndCategory(id, category);
        ArrayList<String> ids  =new ArrayList<>();
        for (UserImage image : images) {
            ids.add(image.getId());
        }
        return ids;
    }
}
