package com.example.initialapi.service;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Profile;
import com.example.initialapi.repository.ProfileRepository;
import com.example.initialapi.validator.ProfileValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService {
    private ProfileRepository profileRepository;
    private ProfileValidator profileValidator;

    @Transactional
    public List<Profile> saveAll(List<Profile> profileList) {
        return profileRepository.saveAll(profileList);
    }

    public DataFormat<Profile> getAll(Integer page, Integer size) {
        DataFormat<Profile> profileDataFormat = new DataFormat<>();
        if (page != null && size != null) {
            profileDataFormat.format(page, size, profileRepository.findAll().size());
            profileDataFormat.setData(
                    profileRepository.findAll(PageRequest.of(page, size)).toList()
            );
            return  profileDataFormat;
        }
        profileDataFormat.setData(profileRepository.findAll());
        return profileDataFormat;
    }

    public Profile getById(int id) {
        return profileRepository.findById(id).get();
    }

    public Profile put(int id, Profile profile) {
        Profile oldProfile = profileRepository.findById(id).get();
        Profile newProfile = profileValidator.validate(oldProfile, profile);
        return profileRepository.save(newProfile);
    }

}
