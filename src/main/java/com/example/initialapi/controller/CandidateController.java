package com.example.initialapi.controller;

import com.example.initialapi.model.Candidate;
import com.example.initialapi.model.DataFormat;
import com.example.initialapi.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/candidate")
public class CandidateController {
    private CandidateService candidateService;

    @GetMapping(value = "")
    public DataFormat<Candidate> getAllCandidate(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
    ) {
        return candidateService.getAll(page, size);
    }

    @PostMapping(value = "")
    public List<Candidate> saveAllCandidate(@RequestBody List<Candidate> candidateList) {
        return candidateService.saveAll(candidateList);
    }

    @GetMapping(value = "/{id}")
    public Candidate getCandidateById(@PathVariable int id) {
        return candidateService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public Candidate putCandidateById(@PathVariable int id, @RequestBody Candidate candidate) {
        return candidateService.putById(id, candidate);
    }
}
