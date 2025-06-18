package com.example.service;

import com.example.dto.LivreDto;

import java.util.List;

public interface LivreService {
    List<LivreDto> getAllLivres();
    LivreDto getLivreById(Long id);
    LivreDto createLivre(LivreDto livreDto);
    LivreDto updateLivre(Long id, LivreDto livreDto);
    void deleteLivre(Long id);
}
