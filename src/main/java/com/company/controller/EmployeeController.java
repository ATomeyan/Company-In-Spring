package com.company.controller;

import com.company.dto.EmployeeDto;
import com.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {

        List<EmployeeDto> employeeDto = service.getAllEmployees();

        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Integer id) {

        EmployeeDto employeeDto = service.getEmployeeById(id);

        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {

        EmployeeDto employee = service.saveEmployee(employeeDto);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EmployeeDto> editEmployee(@PathVariable(name = "id") Integer id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employee = service.update(id, employeeDto);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable Integer id) {

        service.deleteEmployee(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}