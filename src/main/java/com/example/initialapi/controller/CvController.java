package com.example.initialapi.controller;

import com.example.initialapi.model.Cv;
import com.example.initialapi.model.ResponseFile;
import com.example.initialapi.service.CvService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cv")
@CrossOrigin(origins = "*")
public class CvController {
    private CvService cvService;

    @PostMapping(value = "")
    public ResponseEntity<String> saveCv(@RequestParam(name = "cv") MultipartFile cv) {
        try {
            cvService.save(cv);
            return ResponseEntity.status(HttpStatus.OK).body("Uploaded the file successfully: " + cv.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<List<ResponseFile>> getAllCv() {
        return ResponseEntity.ok().body(cvService.getResponseFile());
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable int id) {
        Cv cv = cvService.getById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + cv.getName() + "\"")
                .body(cv.getData());
    }
}
