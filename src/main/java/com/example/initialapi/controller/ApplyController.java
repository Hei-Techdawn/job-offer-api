package com.example.initialapi.controller;

import com.example.initialapi.model.Apply;
import com.example.initialapi.model.DataFormat;
import com.example.initialapi.service.ApplyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/apply")
@CrossOrigin(origins = "*")
public class ApplyController {
    private ApplyService applyService;

    @GetMapping(value = "")
    public DataFormat<Apply> getAllApply(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
    ) {
        return applyService.getAll(page, size);
    }

    @PostMapping(value = "")
    public Apply saveApply(@RequestBody Apply apply) {
        return applyService.save(apply);
    }

    @GetMapping(value = "/{id}")
    public Apply getApplyById(@PathVariable int id) {
        return applyService.getById(id);
    }

}
