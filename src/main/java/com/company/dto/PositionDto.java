package com.company.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class PositionDto implements Serializable {

    @JsonIgnore
    private int id;
    private String name;
}