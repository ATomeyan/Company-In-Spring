package com.company.mapper;

import com.company.dto.PositionDto;
import com.company.entity.Position;

public class PositionMapper {

    public PositionDto entityToDto(Position position) {
        PositionDto positionDto = new PositionDto();

        positionDto.setId(position.getId());
        positionDto.setName(position.getName());

        return positionDto;
    }

    public Position dtoToEntity(PositionDto positionDto) {
        Position position = new Position();

        position.setId(positionDto.getId());
        position.setName(positionDto.getName());

        return position;
    }
}
