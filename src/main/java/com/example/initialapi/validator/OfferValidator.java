package com.example.initialapi.validator;

import com.example.initialapi.model.History;
import com.example.initialapi.model.Offer;
import com.example.initialapi.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class OfferValidator {
    private HistoryRepository historyRepository;
    public Offer validate(Offer oldOffer, Offer offer) {
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
        return oldOffer;
    }

    public Offer changeRef(Offer offer) {
        String ref = "";
        for (int i = 4 - (String.valueOf(offer.getId()).length()); i >= 1; i--) {
            ref += "0";
        }
        offer.setRef("REF" + ref + offer.getId());
        return offer;
    }

    public List<Offer> changeStatusList(List<Offer> offerList) {
        List<Offer> offers = new ArrayList<>();
        for (Offer offer : offerList) {
            offers.add(this.changeStatusAndRef(offer));
        }
        return offers;
    }

    public Offer changeStatusAndRef(Offer offer) {
        try {
            List<History> historyList = historyRepository.findByOffer_Id(offer.getId());
            for (History history : historyList) {
                if (history.getCandidate() != null) {
                    offer.setStatus("unvailable");
                    return this.changeRef(offer);
                }
            }
            offer.setStatus("vailable");
            return this.changeRef(offer);
        } catch (Exception e) {
            offer.setStatus("vailable");
            return this.changeRef(offer);
        }
    }
}
