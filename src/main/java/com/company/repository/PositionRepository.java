package com.company.repository;

import com.company.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    Position findPositionByName(String name);
}