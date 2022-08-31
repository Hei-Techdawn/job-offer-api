package com.example.initialapi.service;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Place;
import com.example.initialapi.model.Profile;
import com.example.initialapi.repository.PlaceRepository;
import com.example.initialapi.validator.PlaceValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PlaceService {
    private PlaceRepository placeRepository;
    private PlaceValidator placeValidator;

    public DataFormat<Place> getAll(Integer page, Integer size) {
        DataFormat<Place> placeDataFormat = new DataFormat<>();
        if (page != null && size != null) {
            placeDataFormat.format(page, size, placeRepository.findAll().size());
            placeDataFormat.setData(
                    placeRepository.findAll(PageRequest.of(page, size)).toList()
            );
            return placeDataFormat;
        }
        placeDataFormat.setData(placeRepository.findAll());
        return placeDataFormat;
    }

    @Transactional
    public List<Place> saveAll(List<Place> placeList) {
        return placeRepository.saveAll(placeList);
    }

    public Place getById(int id) {
        return placeRepository.findById(id).get();
    }

    public Place putById(int id, Place place) {
        Place oldPlace = placeRepository.findById(id).get();
        Place newPlace = placeValidator.validate(oldPlace, place);
        return placeRepository.save(newPlace);
    }
}
