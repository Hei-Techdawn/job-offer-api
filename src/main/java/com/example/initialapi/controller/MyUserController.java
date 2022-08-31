package com.example.initialapi.controller;

import com.example.initialapi.model.MyUser;
import com.example.initialapi.service.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.config.web.servlet.SecurityMarker;
import org.springframework.security.config.web.servlet.headers.HeadersSecurityMarker;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
@CrossOrigin(origins = "*")
public class MyUserController {
    private MyUserService myUserService;

    @PostMapping(value = "")
    public MyUser createUser(
            @RequestBody MyUser myUser
    ) {
        return myUserService.createUser(myUser);
    }

    @GetMapping(value = "")
    public List<MyUser> getUsers(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
    ) {
        return myUserService.getAllUsers(page,size);
    }

    @GetMapping(value = "/{id}")
    public MyUser getUser(@PathVariable int id) {
        return myUserService.getUserById(id);
    }

    @PutMapping(value = "/{id}")
    public MyUser putUser(@PathVariable int id, @RequestBody MyUser myUser) {
        return myUserService.updateUserById(id, myUser);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        myUserService.deleteUser(id);
    }
}
