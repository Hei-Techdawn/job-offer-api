package com.example.initialapi.repository;

import com.example.initialapi.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place,Integer> {
}
