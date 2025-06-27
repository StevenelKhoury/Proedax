package com.example.service;

import com.example.common.LivreMapper;
import com.example.dto.LivreDto;
import com.example.entity.Auteur;
import com.example.entity.Genre;
import com.example.entity.Livre;
import com.example.repository.AuteurRepository;
import com.example.repository.GenreRepository;
import com.example.repository.LivreRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LivreServiceImpl implements LivreService {

    private final LivreRepository livreRepository;
    private final AuteurRepository auteurRepository;
    private final GenreRepository genreRepository;
    private final LivreMapper livreMapper;

    public LivreServiceImpl(
            LivreRepository livreRepository,
            AuteurRepository auteurRepository,
            GenreRepository genreRepository,
            LivreMapper livreMapper
    ) {
        this.livreRepository = livreRepository;
        this.auteurRepository = auteurRepository;
        this.genreRepository = genreRepository;
        this.livreMapper = livreMapper;
    }

    @Override
    public List<LivreDto> getAllLivres() {
        return livreRepository.findAll()
                .stream()
                .map(livreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LivreDto getLivreById(Long id) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre introuvable avec l'id : " + id));
        return livreMapper.toDto(livre);
    }

    @Override
    public LivreDto createLivre(LivreDto dto) {
        Livre livre = livreMapper.toEntity(dto);
        livre.setAuteur(auteurRepository.findById(dto.getAuteur().getId()).orElse(null));
        livre.setGenres(dto.getGenres().stream()
                .map(g -> genreRepository.findById(g.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));
        Livre saved = livreRepository.save(livre);
        return livreMapper.toDto(saved);
    }

    @Transactional
    public LivreDto updateLivre(Long id, LivreDto dto) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livre non trouvé avec id : " + id));

        // Utiliser le mapper pour mettre à jour l'entité
        livreMapper.updateEntityFromDto(dto, livre);

        // Gérer les relations séparément car elles nécessitent des recherches en base
        if (dto.getAuteur() != null && dto.getAuteur().getId() != null) {
            Auteur auteur = auteurRepository.findById(dto.getAuteur().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Auteur non trouvé"));
            livre.setAuteur(auteur);
        }

        if (dto.getGenres() != null) {
            Set<Genre> genres = dto.getGenres().stream()
                    .map(g -> genreRepository.findById(g.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Genre non trouvé avec id : " + g.getId())))
                    .collect(Collectors.toSet());
            livre.setGenres(genres);
        }

        Livre updated = livreRepository.save(livre);
        return livreMapper.toDto(updated);
    }


    @Override
    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }
}
