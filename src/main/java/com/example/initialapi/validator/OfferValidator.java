package com.example.initialapi.validator;

import com.example.initialapi.model.Offer;
import org.springframework.stereotype.Component;

@Component
public class OfferValidator {
    public Offer validate(Offer oldOffer,Offer offer){
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
}
