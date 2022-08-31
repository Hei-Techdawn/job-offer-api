package com.example.initialapi.repository;


import com.example.initialapi.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
