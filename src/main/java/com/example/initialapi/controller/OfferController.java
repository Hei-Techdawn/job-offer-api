package com.example.initialapi.controller;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Offer;
import com.example.initialapi.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/offer")
@CrossOrigin(origins = "*")
public class OfferController {
    private OfferService offerService;

    @GetMapping(value = "")
    public DataFormat<Offer> getAllOffer(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        return offerService.getAll(page, size,sort);
    }

    @GetMapping(value = "/domain/{id}")
    public DataFormat<Offer> getOfferByIdDomain(
            @PathVariable int id,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        return offerService.getByDomainId(page, size, sort, id);
    }

    @GetMapping(value = "/profile/{id}")
    public DataFormat<Offer> getOfferByProfileId(
            @PathVariable int id,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sort", required = false) String sort

    ) {
        return offerService.getByProfileId(page, size,sort, id);
    }

    @PostMapping(value = "")
    public Offer saveOffer(@RequestBody Offer offer) {
        return offerService.save(offer);
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
