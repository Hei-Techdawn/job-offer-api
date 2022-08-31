package com.example.initialapi.controller;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.Position;
import com.example.initialapi.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/position")
public class PositionController {
    private PositionService positionService;

    @GetMapping(value = "")
    public DataFormat<Position> getAllPosition(
            @PathVariable(name = "page", required = false) Integer page,
            @PathVariable(name = "size", required = false) Integer size
    ) {
        return positionService.getAll(page, size);
    }

    @GetMapping(value = "/{id}")
    public Position getPositionById(@PathVariable int id) {
        return positionService.getById(id);
    }

    @PostMapping(value = "")
    public List<Position> saveAllPosition(@RequestBody List<Position> positionList) {
        return positionService.saveAll(positionList);
    }

    @PutMapping(value = "/{id}")
    public Position putPositionById(
            @PathVariable int id,
            @RequestBody Position position
    ) {
        return positionService.putById(id, position);
    }
}
