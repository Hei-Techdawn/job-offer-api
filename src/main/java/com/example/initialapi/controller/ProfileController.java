package com.example.initialapi.controller;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Profile;
import com.example.initialapi.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/profile")
public class ProfileController {
    private ProfileService profileService;

    @GetMapping(value = "")
    public DataFormat<Profile> getAllProfile(
            @PathVariable(name = "page", required = false) Integer page,
            @PathVariable(name = "size", required = false) Integer size
    ) {
        return profileService.getAll(page, size);
    }

    @PostMapping(value = "")
    public List<Profile> saveAllProfile(@RequestBody List<Profile> profileList) {
        return profileService.saveAll(profileList);
    }

    @GetMapping(value = "/{id}")
    public Profile getProfileById(@PathVariable int id) {
        return profileService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public Profile putProfileById(@PathVariable int id, @RequestBody Profile profile) {
        return profileService.put(id, profile);
    }
}
