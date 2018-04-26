package io.chriskelley.blog.controllers;

import io.chriskelley.blog.models.Post;
import io.chriskelley.blog.repos.PostRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    private final PostRepo postDao;

    @Value("${file-upload-path}")
    private String uploadPath;

    public FileUploadController(PostRepo postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts/fileupload")
    public String showUploadFileForm() {
        return "/posts/fileupload";
    }

    @PostMapping("/posts/fileupload")
    public String saveFile(
            @RequestParam(name = "file") MultipartFile uploadedFile,
            Model model
    ) {
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File successfully uploaded!");
            Post post = new Post();
            post.setPath(filepath);
            postDao.save(post);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }
        return "/post/fileupload";
    }
}
