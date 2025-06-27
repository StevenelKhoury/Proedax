package com.example.service;

import com.example.dto.AuteurDto;
import java.util.List;

public interface AuteurService {
    List<AuteurDto> getAllAuteurs();
    AuteurDto getAuteurById(Long id);
    AuteurDto createAuteur(AuteurDto auteurDto);
    AuteurDto updateAuteur(Long id, AuteurDto auteurDto);
    void deleteAuteur(Long id);
}
