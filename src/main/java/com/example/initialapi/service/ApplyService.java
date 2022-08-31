package com.example.initialapi.service;

import com.example.initialapi.model.Apply;
import com.example.initialapi.model.DataFormat;
import com.example.initialapi.repository.ApplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplyService {
    private ApplyRepository applyRepository;

    public DataFormat<Apply> getAllApply(Integer page, Integer size) {
        DataFormat<Apply> applyDataFormat = new DataFormat<>();
        if (page != null && size != null) {
            applyDataFormat.format(page, size, applyRepository.countAll());
            applyDataFormat.setData(
                    applyRepository.findAll(PageRequest.of(page, size, Sort.by("date").descending())).toList()
            );
            return applyDataFormat;
        }
        applyDataFormat.setData(applyRepository.findAll(Sort.by("date").descending()));
        return applyDataFormat;
    }

    public Apply getById(int id) {
        return applyRepository.findById(id).get();
    }
}