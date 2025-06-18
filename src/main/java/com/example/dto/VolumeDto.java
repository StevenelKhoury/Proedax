package com.example.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class VolumeDto {
    private Long id;
    private int numero;
    private LocalDate datePublication;
    private List<ChapitreDto> chapitres;
}