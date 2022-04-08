package com.company.controller;

import com.company.entity.Position;
import com.company.service.PositionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PositionController {

    private final PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }

    @GetMapping("/company/position")
    public List<Position> getPositions(){
        return service.getAllPosition();
    }
}