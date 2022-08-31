package com.example.initialapi.validator;

import com.example.initialapi.model.Position;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PositionValidator {
    public Position validate(Position oldPosition, Position position) {
        if (position.getPosition() != null) {
            oldPosition.setPosition(position.getPosition());
        }
        return oldPosition;
    }
}
