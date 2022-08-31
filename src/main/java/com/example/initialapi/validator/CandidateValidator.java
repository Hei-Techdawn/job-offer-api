package com.example.initialapi.validator;

import com.example.initialapi.model.Candidate;

public class CandidateValidator {
    public Candidate validate(Candidate oldCandidate, Candidate candidate) {
        if (candidate.getLastName() != null) {
            oldCandidate.setLastName(candidate.getLastName());
        }
        if (candidate.getFirstName() != null) {
            oldCandidate.setFirstName(candidate.getFirstName());
        }
        if (candidate.getEmail() != null) {
            oldCandidate.setEmail(candidate.getEmail());
        }
        if (candidate.getAddress() != null) {
            oldCandidate.setAddress(candidate.getAddress());
        }
        return oldCandidate;
    }
}
