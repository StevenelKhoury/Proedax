package com.example.service;

import com.example.common.AuteurMapper;
import com.example.dto.AuteurDto;
import com.example.entity.Auteur;
import com.example.repository.AuteurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuteurServiceImpl implements AuteurService {

    private final AuteurRepository auteurRepository;
    private final AuteurMapper auteurMapper;

    public AuteurServiceImpl(AuteurRepository auteurRepository, AuteurMapper auteurMapper) {
        this.auteurRepository = auteurRepository;
        this.auteurMapper = auteurMapper;
    }

    @Override
    public List<AuteurDto> getAllAuteurs() {
        return auteurRepository.findAll()
                .stream()
                .map(auteurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuteurDto getAuteurById(Long id) {
        Auteur auteur = auteurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auteur non trouvé avec l'id : " + id));
        return auteurMapper.toDto(auteur);
    }

    @Override
    public AuteurDto createAuteur(AuteurDto auteurDto) {
        Auteur auteur = auteurMapper.toEntity(auteurDto);
        Auteur saved = auteurRepository.save(auteur);
        return auteurMapper.toDto(saved);
    }

    @Override
    @Transactional
    public AuteurDto updateAuteur(Long id, AuteurDto auteurDto) {
        Auteur auteur = auteurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auteur non trouvé avec l'id : " + id));

        auteurMapper.updateEntityFromDto(auteurDto, auteur);
        Auteur updated = auteurRepository.save(auteur);
        return auteurMapper.toDto(updated);
    }

    @Override
    public void deleteAuteur(Long id) {
        auteurRepository.deleteById(id);
    }
}

