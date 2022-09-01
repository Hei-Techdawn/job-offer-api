package com.example.initialapi.service;

import com.example.initialapi.model.Apply;
import com.example.initialapi.model.Candidate;
import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Domain;
import com.example.initialapi.repository.ApplyRepository;
import com.example.initialapi.repository.CandidateRepository;
import com.example.initialapi.repository.DomainRepository;
import com.example.initialapi.validator.CandidateValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplyService {
    private ApplyRepository applyRepository;

    public DataFormat<Apply> getAll(Integer page, Integer size) {
        DataFormat<Apply> applyDataFormat = new DataFormat<>();
        if (page != null && size != null) {
            applyDataFormat.format(page, size, applyRepository.findAll().size());
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

    public Apply save(Apply apply) {
        return applyRepository.save(apply);
    }
}
