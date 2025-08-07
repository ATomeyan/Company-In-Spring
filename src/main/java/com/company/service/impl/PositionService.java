package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.PositionDto;
import com.company.entity.Position;
import com.company.exceptions.NotFoundException;
import com.company.exceptions.NotValidException;
import com.company.mapper.PositionMapper;
import com.company.repository.PositionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionService implements com.company.service.PositionService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PositionService.class);
    private final PositionMapper positionMapper;
    private final PositionRepository repository;

    @Autowired
    public PositionService(PositionRepository repository) {
        this.repository = repository;
        positionMapper = new PositionMapper();
    }

    @Override
    public List<PositionDto> getAllPosition() {

        return getPositionDto(repository.findAll());
    }

    @Override
    public PositionDto getPositionByName(String name) {
        if (name == null || name.isEmpty()){
            LOGGER.error("Position name is not valid {}.", name);
            throw new NotValidException("Position name is not valid.");
        }

        Position position = repository.findPositionByName(name).orElse(null);
        if (position == null){
            LOGGER.error("Position by name {} is not found.", name);
            throw new NotFoundException("Position is not found.");
        }

        return positionMapper.entityToDto(position);
    }

    private List<PositionDto> getPositionDto(List<Position> position) {

        List<PositionDto> positions = new ArrayList<>();

        for (Position p : position) {
            PositionDto positionDto = positionMapper.entityToDto(p);
            positions.add(positionDto);
        }

        return positions;
    }
}