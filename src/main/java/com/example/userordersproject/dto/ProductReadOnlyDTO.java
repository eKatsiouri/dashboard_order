package com.example.userordersproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReadOnlyDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
}
