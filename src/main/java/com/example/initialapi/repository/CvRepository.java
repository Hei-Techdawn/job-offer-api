package com.example.initialapi.repository;

import com.example.initialapi.model.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvRepository extends JpaRepository<Cv,Integer> {
}
