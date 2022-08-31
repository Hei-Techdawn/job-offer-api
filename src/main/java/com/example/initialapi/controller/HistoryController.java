package com.example.initialapi.controller;

import com.example.initialapi.model.DataFormat;
import com.example.initialapi.model.History;
import com.example.initialapi.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/history")
public class HistoryController {
    private HistoryService historyService;

    @GetMapping(value = "")
    public DataFormat<History> getAllHistory(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
    ) {
        return historyService.getAll(page, size);
    }

    @PostMapping(value = "")
    public History saveHistory(@RequestBody History history) {
        return historyService.save(history);
    }

    @GetMapping(value = "/{id}")
    public History getHistoryById(@PathVariable int id) {
        return historyService.getById(id);
    }
}
