package com.example.dto;

import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class LivreDto {
    private Long id;
    private String titre;
    private String description;
    private AuteurDto auteur;
    private Set<GenreDto> genres;
    private List<VolumeDto> volumes;
}
