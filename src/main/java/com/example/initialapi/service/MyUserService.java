package com.example.initialapi.service;

import com.example.initialapi.model.MyUser;
import com.example.initialapi.repository.MyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MyUserService {
    private MyUserRepository myUserRepository;

    public MyUser createUser(MyUser myUser) {
        myUser.setPassword(passwordEncoder().encode(myUser.getPassword()));
        return myUserRepository.save(myUser);
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<MyUser> getAllUsers(Integer page, Integer size) {
        if (page != null && size != null) {
            return myUserRepository.findAll(PageRequest.of(page, size)).toList();
        }
        return myUserRepository.findAll();
    }

    public MyUser getUserById(int id) {
        return myUserRepository.findById(id).get();
    }

    public MyUser updateUserById(int id, MyUser myUser) {
        MyUser oldMyUser = myUserRepository.findById(id).get();
        if (myUser.getLastname() != null) {
            oldMyUser.setLastname(myUser.getLastname());
        }
        if (myUser.getFirstname() != null) {
            oldMyUser.setFirstname(myUser.getFirstname());
        }
        if (myUser.getUsername() != null) {
            oldMyUser.setUsername(myUser.getUsername());
        }
        if (myUser.getPassword() != null) {
            oldMyUser.setPassword(passwordEncoder().encode(myUser.getPassword()));
        }
        return myUserRepository.save(oldMyUser);
    }

    public void deleteUser(int id) {
        myUserRepository.deleteById(id);
    }
}
