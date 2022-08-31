package com.example.initialapi.validator;

import com.example.initialapi.model.Profile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProfileValidator {

    public Profile validate(Profile oldProfile, Profile newProfile) {
        if(newProfile.getProfile() != null) {
            oldProfile.setProfile(newProfile.getProfile());
        }
        return oldProfile;
    }
}
