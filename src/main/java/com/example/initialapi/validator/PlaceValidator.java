package com.example.initialapi.validator;

import com.example.initialapi.model.Place;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaceValidator {
    public Place validate(Place oldPlace, Place place) {
        if (place.getName() != null) {
            oldPlace.setName(place.getName());
        }
        return oldPlace;
    }
}
