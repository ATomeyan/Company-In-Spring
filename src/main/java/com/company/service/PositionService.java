package com.company.service;

import com.company.entity.Position;

import java.util.List;

public interface PositionService {

    Position getPositionById(int id);

    List<Position> getAllPosition();

    Position getPositionByName(String name);
}