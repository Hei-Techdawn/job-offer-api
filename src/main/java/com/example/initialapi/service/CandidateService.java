package com.example.initialapi.service;

import com.example.initialapi.model.Candidate;
import com.example.initialapi.model.DataFormat;
import com.example.initialapi.repository.CandidateRepository;
import com.example.initialapi.validator.CandidateValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CandidateService {
    private CandidateRepository candidateRepository;
    private CandidateValidator candidateValidator;

    public DataFormat<Candidate> getAll(Integer page, Integer size) {
        DataFormat<Candidate> candidateDataFormat = new DataFormat<>();
        if (page != null && size != null) {
            candidateDataFormat.format(page, size, candidateRepository.findAll().size());
            candidateDataFormat.setData(
                    candidateRepository.findAll(PageRequest.of(page, size)).toList()
            );
            return candidateDataFormat;
        }
        candidateDataFormat.setData(candidateRepository.findAll());
        return candidateDataFormat;
    }

    @Transactional
    public List<Candidate> saveAll(List<Candidate> candidateList) {
        return candidateRepository.saveAll(candidateList);
    }

    public Candidate putById(int id, Candidate candidate) {
        Candidate oldCandidate = candidateRepository.findById(id).get();
        Candidate newCandidate = candidateValidator.validate(oldCandidate, candidate);
        return candidateRepository.save(newCandidate);
    }
}
