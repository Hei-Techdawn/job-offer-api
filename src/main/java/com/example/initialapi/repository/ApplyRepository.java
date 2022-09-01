package com.example.initialapi.repository;


import com.example.initialapi.model.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply,Integer> {
    List<Apply> findAllByOffer_Id(Integer offerId);
}
