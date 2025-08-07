package com.company.service;

import com.company.dto.PositionDto;

import java.util.List;

public interface PositionService {

    List<PositionDto> getAllPosition();

    PositionDto getPositionByName(String name);
}