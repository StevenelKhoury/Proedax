package com.example.dto;

import lombok.Data;

@Data
public class ChapitreDto {
    private Long id;
    private int numero;
    private String titre;
    private int nombrePages;
}