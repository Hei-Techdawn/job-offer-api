package com.example.initialapi.service;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Domain;
import com.example.initialapi.model.Offer;
import com.example.initialapi.repository.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OfferService {
    private OfferRepository offerRepository;

    //    TODO  : change status and ref value
    public DataFormat<Offer> getAll(Integer page, Integer size) {
        DataFormat<Offer> dataFormat = new DataFormat<>();
        if (page != null && size != null) {
            dataFormat.format(page, size, offerRepository.countOffer());
            dataFormat.setData(
                    offerRepository.findAll(PageRequest.of(page, size)).toList()
            );
            return dataFormat;
        }
        dataFormat.setData(offerRepository.findAll());
        return dataFormat;
    }

    public DataFormat<Offer> getByDomainId(Integer page, Integer size, Integer idDomain) {
        DataFormat<Offer> offerDataFormat = this.getAll(page, size);
        List<Offer> offerList = offerDataFormat.getData();
        List<Offer> offers = new ArrayList<>();
        for (Offer offer : offerList) {
            if (offer.getDomain().getId() == idDomain) {
                offers.add(offer);
            }
        }
        offerDataFormat.setData(offers);
        return offerDataFormat;
    }

    @Transactional
    public List<Offer> saveAll(List<Offer> offerList) {
        return offerRepository.saveAll(offerList);
    }

    public Offer getById(int id) {
        return offerRepository.findById(id).get();
    }

    public Offer putById(int id, Offer offer) {
        Offer oldOffer = offerRepository.findById(id).get();
        if (offer.getName() != null) {
            oldOffer.setName(offer.getName());
        }
        if (offer.getProfile() != null) {
            oldOffer.setProfile(offer.getProfile());
        }
        if (offer.getPlace() != null) {
            oldOffer.setPlace(offer.getPlace());
        }
        if (offer.getDomain() != null) {
            oldOffer.setDomain(offer.getDomain());
        }
        return offerRepository.save(oldOffer);
    }
}
