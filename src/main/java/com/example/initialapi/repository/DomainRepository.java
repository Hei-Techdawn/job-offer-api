package com.example.initialapi.repository;

import com.example.initialapi.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Integer> {
}
