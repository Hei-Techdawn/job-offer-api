package com.example.initialapi.controller;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Place;
import com.example.initialapi.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/place")
public class PlaceController {
    private PlaceService placeService;

    @GetMapping(value = "")
    public DataFormat<Place> getAllPlace(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
    ) {
        return placeService.getAll(page, size);
    }

    @PostMapping(value = "")
    public List<Place> saveAllPlace(@RequestBody List<Place> placeList) {
        return placeService.saveAll(placeList);
    }

    @GetMapping(value = "/{id}")
    public Place getPlaceById(@PathVariable int id) {
        return placeService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public Place putPlaceById(@PathVariable int id, @RequestBody Place place) {
        return placeService.putById(id, place);
    }
}
