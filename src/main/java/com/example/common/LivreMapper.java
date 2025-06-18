package com.example.common;

import com.example.dto.AuteurDto;
import com.example.dto.GenreDto;
import com.example.dto.LivreDto;
import com.example.entity.Livre;
import org.springframework.stereotype.Component;

@Component
public class LivreMapper {

    public LivreDto toDto(Livre livre) {
        LivreDto dto = new LivreDto();
        dto.setId(livre.getId());
        dto.setTitre(livre.getTitre());
        dto.setDescription(livre.getDescription());

        AuteurDto auteurDto = new AuteurDto();
        auteurDto.setId(livre.getAuteur().getId());
        auteurDto.setNom(livre.getAuteur().getNom());
        auteurDto.setNationalite(livre.getAuteur().getNationalite());
        dto.setAuteur(auteurDto);

        Set<GenreDto> genres = livre.getGenres().stream().map(g -> {
            GenreDto gd = new GenreDto();
            gd.setId(g.getId());
            gd.setNom(g.getNom());
            return gd;
        }).collect(Collectors.toSet());
        dto.setGenres(genres);

        // ici on ne mappe pas les volumes pour rester simple
        dto.setVolumes(new ArrayList<>());

        return dto;
    }

    public Livre toEntity(LivreDto dto) {
        Livre livre = new Livre();
        livre.setId(dto.getId());
        livre.setTitre(dto.getTitre());
        livre.setDescription(dto.getDescription());
        // les autres champs (auteur, genres, volumes) seront gérés dans le service
        return livre;
    }
}
