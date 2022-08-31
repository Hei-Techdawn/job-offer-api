package com.example.initialapi.service;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Domain;
import com.example.initialapi.repository.DomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DomainService {
    private DomainRepository domainRepository;
//  TODO    : change candidateNumber value;
    public DataFormat<Domain> getAll(Integer page, Integer size) {
        DataFormat<Domain> dataFormat = new DataFormat<>();
        if (page != null && size != null) {
            dataFormat.format(page, size, domainRepository.findAll().size());
            dataFormat.setData(
                    domainRepository.findAll(PageRequest.of(page, size)).toList()
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
        if (!Objects.equals(domain.getName(), oldDomain.getName())) {
            oldDomain.setName(domain.getName());
        }
        if (!Objects.equals(domain.getDescription(), oldDomain.getDescription())) {
            oldDomain.setDescription(oldDomain.getDescription());
        }
        return domainRepository.save(oldDomain);
    }
}
