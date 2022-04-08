package com.company.service.impl;

import com.company.entity.Position;
import com.company.repository.PositionRepository;
import com.company.service.PositionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;

    public PositionServiceImpl(PositionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Position getPositionById(int id) {
        return repository.getById(id);
    }

    @Override
    public List<Position> getAllPosition() {
        List<Position> positions = new ArrayList<>();
        Iterable<Position> iterable = repository.findAll();

        for (Position p : iterable) {
            positions.add(p);
        }

        return positions;
    }

    @Override
    public Position getPositionByName(String name) {
        return repository.findPositionByName(name);
    }
}