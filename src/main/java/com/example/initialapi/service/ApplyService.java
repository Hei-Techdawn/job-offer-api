package com.example.initialapi.service;

import com.example.initialapi.model.Apply;
import com.example.initialapi.model.DataFormat;
import com.example.initialapi.repository.ApplyRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApplyService {
    private ApplyRepository applyRepository;

    public DataFormat<Apply> getAll(Integer page, Integer size) {
        DataFormat<Apply> applyDataFormat = new DataFormat<>();
        if (page != null && size != null) {
            applyDataFormat.format(page, size, applyRepository.findAll().size());
            applyDataFormat.setData(
                    applyRepository.findAll(PageRequest.of(page, size, Sort.by("date").descending())).toList()
            );
            return applyDataFormat;
        }
        applyDataFormat.setData(applyRepository.findAll(Sort.by("date").descending()));
        return applyDataFormat;
    }

    public DataFormat<Apply> getByOfferId(int offer) {
        DataFormat<Apply> applyDataFormat = new DataFormat<>();
        applyDataFormat.setData(applyRepository.findAllByOffer_Id(offer));
        return applyDataFormat;
    }

    public DataFormat<Apply> getByDomainId(int domainId) {
        DataFormat<Apply> applyDataFormat = new DataFormat<>();
        List<Apply> all = applyRepository.findAll(Sort.by("date").descending());
        List<Apply> allByDomain = new ArrayList<>();
        for (Apply apply : all) {
            if (apply.getOffer().getDomain().getId() == domainId) {
                allByDomain.add(apply);
            }
        }
        applyDataFormat.setData(allByDomain);
        return applyDataFormat;
    }

    public Apply getById(int id) {
        return applyRepository.findById(id).get();
    }

    public Apply save(Apply apply) {
        return applyRepository.save(apply);
    }
}
