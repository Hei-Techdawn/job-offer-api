package com.example.initialapi.service;

import com.example.initialapi.model.Cv;
import com.example.initialapi.model.ResponseFile;
import com.example.initialapi.repository.CvRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CvService {
    private CvRepository cvRepository;

    public Cv save(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Cv cv = new Cv();
        cv.setName(fileName);
        cv.setType(file.getContentType());
        cv.setData(file.getBytes());
        return cvRepository.save(cv);
    }

    public Cv getById(int id) {
        return cvRepository.findById(id).get();
    }

    public List<Cv> getAll() {
        return cvRepository.findAll();
    }

    public List<ResponseFile> getResponseFile() {
        List<ResponseFile> responseFileList = new ArrayList<>();
        List<Cv> cvList = this.getAll();
        for (Cv cv : cvList) {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/cv/")
                    .path(String.valueOf(cv.getId()))
                    .toUriString();
            responseFileList.add(new ResponseFile(cv.getId(),cv.getName(), fileDownloadUri, cv.getType(), cv.getData().length));
        }
        return responseFileList;
    }
}
