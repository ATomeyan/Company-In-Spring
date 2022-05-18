package com.company.service;

import com.company.dto.PositionDto;

import java.util.List;

public interface IPositionService {

    List<PositionDto> getAllPosition();

    PositionDto getPositionByName(String name);
}