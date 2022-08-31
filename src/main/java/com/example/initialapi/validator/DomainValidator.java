package com.example.initialapi.validator;

import com.example.initialapi.model.Domain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DomainValidator {
    public Domain validate(Domain oldDomain, Domain domain) {
        if (domain.getName() != null) {
            oldDomain.setName(domain.getName());
        }
        if (domain.getDescription() != null) {
            oldDomain.setDescription(domain.getDescription());
        }
        return oldDomain;
    }
}
