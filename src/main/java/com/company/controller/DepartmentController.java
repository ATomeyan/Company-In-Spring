package com.company.controller;

import com.company.dto.DepartmentDto;
import com.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {

        List<DepartmentDto> dto = service.getAllDepartments();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<DepartmentDto> getDepartmentByName(@PathVariable String name) {

        DepartmentDto dto = service.getByDepartmentName(name);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}