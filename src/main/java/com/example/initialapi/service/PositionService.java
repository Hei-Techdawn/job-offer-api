package com.example.initialapi.service;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Position;
import com.example.initialapi.model.Profile;
import com.example.initialapi.repository.PositionRepository;
import com.example.initialapi.validator.PositionValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PositionService {
    private PositionRepository positionRepository;
    private PositionValidator positionValidator;

    public DataFormat<Position> getAll(Integer page, Integer size) {
        DataFormat<Position> profileDataFormat = new DataFormat<>();
        if (page != null && size != null) {
            profileDataFormat.format(page, size, positionRepository.findAll().size());
            profileDataFormat.setData(
                    positionRepository.findAll(PageRequest.of(page, size)).toList()
            );
            return profileDataFormat;
        }
        profileDataFormat.setData(positionRepository.findAll());
        return profileDataFormat;
    }

    public Position getById(int id) {
        return positionRepository.findById(id).get();
    }

    @Transactional
    public List<Position> saveAll(List<Position> positionList) {
        return positionRepository.saveAll(positionList);
    }

    public Position putById(int id, Position position) {
        Position oldPosition = positionRepository.findById(id).get();
        Position newPosition = positionValidator.validate(oldPosition, position);
        return positionRepository.save(newPosition);
    }
}
