package com.example.initialapi.controller;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Domain;
import com.example.initialapi.service.DomainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/domain")
public class DomainController {
    private DomainService domainService;

    @GetMapping(value = "")
    public DataFormat<Domain> getDomainList(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
    ) {
        return domainService.getAll(page, size);
    }

    @PostMapping(value = "")
    public List<Domain> saveAllDomain(@RequestBody List<Domain> domainList) {
        return domainService.saveAll(domainList);
    }

    @GetMapping(value = "/{id}")
    public Domain getDomainById(@PathVariable int id) {
        return domainService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public Domain putDomainById(
            @PathVariable int id,
            @RequestBody Domain domain
    ) {
        return domainService.put(id, domain);
    }
}
