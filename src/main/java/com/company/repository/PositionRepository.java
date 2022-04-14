package com.company.repository;

import com.company.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    Optional<Position> findPositionByName(String name);
}