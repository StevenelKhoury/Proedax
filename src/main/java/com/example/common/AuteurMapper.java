package com.example.common;


import com.example.dto.AuteurDto;
import com.example.entity.Auteur;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuteurMapper {

    public AuteurDto toDto(Auteur auteur) {
        if (auteur == null) {
            return null;
        }

        AuteurDto dto = new AuteurDto();
        dto.setId(auteur.getId());
        dto.setNom(auteur.getNom());
        dto.setNationalite(auteur.getNationalite());

        return dto;
    }

    public Auteur toEntity(AuteurDto dto) {
        if (dto == null) {
            return null;
        }

        Auteur auteur = new Auteur();
        auteur.setId(dto.getId());
        auteur.setNom(dto.getNom());
        auteur.setNationalite(dto.getNationalite());
        // La liste des livres sera gérée par le service
        auteur.setLivres(new ArrayList<>());

        return auteur;
    }

    public void updateEntityFromDto(AuteurDto dto, Auteur auteur) {
        if (dto == null || auteur == null) {
            return;
        }

        if (dto.getNom() != null) {
            auteur.setNom(dto.getNom());
        }
        if (dto.getNationalite() != null) {
            auteur.setNationalite(dto.getNationalite());
        }
        // On ne modifie pas la liste des livres ici car elle est gérée par la relation JPA
    }
}
