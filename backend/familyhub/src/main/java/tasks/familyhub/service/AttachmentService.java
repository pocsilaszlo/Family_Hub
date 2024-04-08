package tasks.familyhub.service;


import org.springframework.web.multipart.MultipartFile;
import tasks.familyhub.entity.UserImage;

import javax.swing.*;
import java.util.List;

public interface AttachmentService {
    UserImage saveAttachment(MultipartFile file, String id,  String category) throws Exception;

    UserImage getAttachment(String fileId) throws Exception;

    public List<String> getAllIdByUser(String id,  String category);
}
