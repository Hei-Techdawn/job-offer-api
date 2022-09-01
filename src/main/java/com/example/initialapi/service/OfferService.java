package com.example.initialapi.service;

import com.example.initialapi.model.*;
import com.example.initialapi.repository.ApplyRepository;
import com.example.initialapi.repository.HistoryRepository;
import com.example.initialapi.repository.OfferRepository;
import com.example.initialapi.validator.OfferValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OfferService {
    private OfferRepository offerRepository;
    private HistoryRepository historyRepository;
    private OfferValidator offerValidator;
    private ApplyRepository applyRepository;

    public DataFormat<Offer> getAll(Integer page, Integer size) {
        DataFormat<Offer> dataFormat = new DataFormat<>();
        if (page != null && size != null) {
            dataFormat.format(page, size, offerRepository.findAll().size());
            dataFormat.setData(
                    offerValidator.changeCountCandidateList(
                            offerValidator.changeStatusAndRefList(
                                    offerRepository.findAll(PageRequest.of(page, size)).toList()
                            )
                    )
            );
            return dataFormat;
        }
        dataFormat.setData(
                offerValidator.changeCountCandidateList(
                        offerValidator.changeStatusAndRefList(
                                offerRepository.findAll()
                        )
                )
        );
        return dataFormat;
    }

    public DataFormat<Offer> getByDomainId(Integer page, Integer size, Integer domaineId) {
        DataFormat<Offer> offerDataFormat = this.getAll(page, size);
        List<Offer> offerList = offerDataFormat.getData();
        List<Offer> offers = new ArrayList<>();
        for (Offer offer : offerList) {
            if (offer.getDomain().getId() == domaineId) {
                offers.add(offer);
            }
        }
        offerDataFormat.setData(
                offerValidator.changeCountCandidateList(
                        offerValidator.changeStatusAndRefList(offers)
                )
        );
        return offerDataFormat;
    }

    public DataFormat<Offer> getByProfileId(Integer page, Integer size, Integer profileId) {
        DataFormat<Offer> offerDataFormat = this.getAll(page, size);
        List<Offer> offerList = offerDataFormat.getData();
        List<Offer> offers = new ArrayList<>();
        for (Offer offer : offerList) {
            if (offer.getProfile().getId() == profileId) {
                offers.add(offer);
            }
        }
        offerDataFormat.setData(
                offerValidator.changeCountCandidateList(
                        offerValidator.changeStatusAndRefList(offers)
                )
        );
        return offerDataFormat;
    }

    public Offer save(Offer offer) {
        Offer newOffer = offerValidator.changeStatusAndRef(
                offerValidator.changeCountCandidate(
                        offerRepository.save(offer)
                )
        );
        History history = new History();
        history.setOffer(newOffer);
        historyRepository.save(history);
        return newOffer;
    }

    public Offer getById(int id) {
        return offerValidator.changeStatusAndRef(
                offerValidator.changeCountCandidate(
                        offerRepository.findById(id).get()
                )
        );
    }

    public Offer putById(int id, Offer offer) {
        Offer oldOffer = offerRepository.findById(id).get();
        Offer newOffer = offerValidator.validate(oldOffer, offer);
        return offerRepository.save(newOffer);
    }
}
