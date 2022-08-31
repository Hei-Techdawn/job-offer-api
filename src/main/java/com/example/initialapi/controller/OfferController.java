package com.example.initialapi.controller;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Offer;
import com.example.initialapi.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "offer")
public class OfferController {
    private OfferService offerService;

    @GetMapping(value = "")
    public DataFormat<Offer> getAllOffer(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
    ) {
        return offerService.getAll(page, size);
    }

    @PostMapping(value = "")
    public List<Offer> saveAllOffer(@RequestBody List<Offer> offerList) {
        return offerService.saveAll(offerList);
    }

    @GetMapping(value = "/{id}")
    public Offer getOfferById(@PathVariable int id) {
        return offerService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public Offer putOfferById(@PathVariable int id, @RequestBody Offer offer) {
        return offerService.putById(id, offer);
    }
}