package com.example.initialapi.service;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.History;
import com.example.initialapi.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoryService {
    private HistoryRepository historyRepository;

    public DataFormat<History> getAll(Integer page, Integer size) {
        DataFormat<History> historyDataFormat = new DataFormat<>();
        if (page != null && size != null) {
            historyDataFormat.format(page, size, historyRepository.findAll().size());
            historyDataFormat.setData(
                    historyRepository.findAll(PageRequest.of(page, size, Sort.by("date").descending())).toList()
            );
            return historyDataFormat;
        }
        historyDataFormat.setData(
                historyRepository.findAll(Sort.by("date").descending())
        );
        return historyDataFormat;
    }

    public History getById(int id) {
        return historyRepository.findById(id).get();
    }

    public History save(History history) {
        return historyRepository.save(history);
    }
}
