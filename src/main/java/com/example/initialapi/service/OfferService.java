package com.example.initialapi.service;

import com.example.initialapi.model.*;
import com.example.initialapi.repository.HistoryRepository;
import com.example.initialapi.repository.OfferRepository;
import com.example.initialapi.validator.OfferValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OfferService {
    private OfferRepository offerRepository;
    private HistoryRepository historyRepository;
    private OfferValidator offerValidator;

    //    TODO  : change status and ref value
    public DataFormat<Offer> getAll(Integer page, Integer size) {
        DataFormat<Offer> dataFormat = new DataFormat<>();
        if (page != null && size != null) {
            dataFormat.format(page, size, offerRepository.findAll().size());
            dataFormat.setData(
                    offerRepository.findAll(PageRequest.of(page, size)).toList()
            );
            return dataFormat;
        }
        dataFormat.setData(this.changeStatusList(offerRepository.findAll()));
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

    public Offer changeStatus(Offer offer) {
        try {
            History history = historyRepository.findByOffer_IdAndType(offer.getId(), "unvailable");
            offer.setStatus("unvailable");
            return offer;
        } catch (Exception e) {
            offer.setStatus("vailable");
            return offer;
        }
    }

    public List<Offer> changeStatusList(List<Offer> offerList) {
        List<Offer> offers = new ArrayList<>();
        for (Offer offer : offerList) {
            offers.add(this.changeStatus(offer));
        }
        return offers;
    }

    @Transactional
    public List<Offer> saveAll(List<Offer> offerList) {
        return offerRepository.saveAll(offerList);
    }

    public Offer getById(int id) {
        return this.changeStatus(offerRepository.findById(id).get());
    }

    public Offer putById(int id, Offer offer) {
        Offer oldOffer = offerRepository.findById(id).get();
        Offer newOffer = offerValidator.validate(oldOffer, offer);
        return offerRepository.save(newOffer);
    }
}
