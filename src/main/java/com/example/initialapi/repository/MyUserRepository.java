package com.example.initialapi.repository;

import com.example.initialapi.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {

    MyUser findByUsername(String username);
}
