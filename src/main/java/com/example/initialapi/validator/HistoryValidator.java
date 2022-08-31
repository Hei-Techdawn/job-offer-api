package com.example.initialapi.validator;

import com.example.initialapi.model.History;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class HistoryValidator {
    private OfferValidator offerValidator;

    public History transientOfferInHistory(History history) {
        history.setOffer(offerValidator.changeStatusAndRef(history.getOffer()));
        return history;
    }

    public List<History> transientOfferInHistoryList(List<History> historyList) {
        List<History> histories = new ArrayList<>();
        for (History history : historyList) {
            histories.add(this.transientOfferInHistory(history));
        }
        return histories;
    }
}
