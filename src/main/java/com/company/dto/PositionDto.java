package com.company.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PositionDto implements Serializable {
    private final int id;
    private final String name;
}