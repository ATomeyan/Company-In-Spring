package com.company.controller;

import com.company.service.ExcelGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Artur Tomeyan
 * @date 05/09/2022
 */
@CrossOrigin
@RestController
@RequestMapping("/download")
public class ExcelGeneratorController {

    private final ExcelGenerateService generateService;

    @Autowired
    public ExcelGeneratorController(ExcelGenerateService generateService) {
        this.generateService = generateService;
    }

    @GetMapping
    public ResponseEntity<Resource> getFile() {
        String filename = "employees.xlsx";
        InputStreamResource file = new InputStreamResource(generateService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }
}