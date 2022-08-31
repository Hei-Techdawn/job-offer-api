package com.example.initialapi.service;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Domain;
import com.example.initialapi.repository.DomainRepository;
import com.example.initialapi.validator.DomainValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DomainService {
    private DomainRepository domainRepository;
    private DomainValidator domainValidator;

    //  TODO    : change candidateNumber value;
    public DataFormat<Domain> getAll(Integer page, Integer size) {
        DataFormat<Domain> dataFormat = new DataFormat<>();
        if (page != null && size != null) {
            dataFormat.format(page, size, domainRepository.findAll().size());
            dataFormat.setData(
                    domainRepository.findAll(PageRequest.of(page, size, Sort.by("candidateNumber").descending())).toList()
            );
            return dataFormat;
        }
        dataFormat.setData(domainRepository.findAll());
        return dataFormat;
    }

    @Transactional
    public List<Domain> saveAll(List<Domain> domainList) {
        return domainRepository.saveAll(domainList);
    }

    public Domain getById(int id) {
        return domainRepository.findById(id).get();
    }

    public Domain put(int id, Domain domain) {
        Domain oldDomain = domainRepository.findById(id).get();
        Domain newDomain = domainValidator.validate(oldDomain, domain);
        return domainRepository.save(newDomain);
    }
}
