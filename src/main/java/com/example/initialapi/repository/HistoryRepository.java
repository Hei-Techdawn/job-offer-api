package com.example.initialapi.repository;

import com.example.initialapi.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History,Integer> {
}
