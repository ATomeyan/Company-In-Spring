package com.company.controller;

import com.company.dto.PositionDto;
import com.company.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/positions")
public class PositionController {

    private final PositionService service;

    @Autowired
    public PositionController(PositionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PositionDto>> getPositions() {

        List<PositionDto> dto = service.getAllPosition();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<PositionDto> getPositionByName(@PathVariable String name) {

        PositionDto dto = service.getPositionByName(name);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}