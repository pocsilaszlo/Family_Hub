package tasks.familyhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tasks.familyhub.dto.ImageUrlResponse;
import tasks.familyhub.entity.UserImage;
import tasks.familyhub.service.AttachmentService;

import java.util.List;

@RestController
public class AttachmentController {

    private final AttachmentService attachmentService;


    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload/{category}/{id}")
    public ImageUrlResponse uploadFile(@PathVariable String category, @PathVariable String id, @RequestParam("file")MultipartFile file) throws Exception {
        UserImage attachment = null;
        String downloadURl = "";

        attachment = (UserImage) attachmentService.saveAttachment(file, id, category);
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();


        return new ImageUrlResponse(attachment.getName(),
                downloadURl,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        UserImage attachment = null;
        try {
            attachment = (UserImage) attachmentService.getAttachment(fileId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getImageData()));
    }



    @GetMapping("/images/{category}/{id}")
    public ResponseEntity<List<String>> getImageLinks(@PathVariable String category,@PathVariable String id) {
        return new ResponseEntity<>(attachmentService.getAllIdByUser(id, category), HttpStatus.OK);
    }
}
