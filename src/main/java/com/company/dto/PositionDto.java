package com.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class PositionDto implements Serializable {

    private Integer id;
    private String name;
}