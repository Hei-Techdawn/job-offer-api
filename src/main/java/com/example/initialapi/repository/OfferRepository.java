package com.example.initialapi.repository;


import com.example.initialapi.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    int countOffer();
}
