package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                // Specify the directory where you want to save the uploaded file
                String uploadDirectory = "C:\\Users\\admin\\eclipse-workspace\\FileUpload\\";
                File uploadPath = new File(uploadDirectory);

                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                file.transferTo(new File(uploadDirectory + originalFilename));
                model.addAttribute("message", "File uploaded successfully: " + originalFilename);
                return "success";
            } catch (IOException e) {
                model.addAttribute("message", "Failed to upload file");
                e.printStackTrace();
                return "error";
            }
        } else {
            model.addAttribute("message", "No file selected");
            return "error";
        }
    }
}
