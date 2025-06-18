package com.example.repository;

import com.example.entity.Chapitre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapitreRepository extends JpaRepository<Chapitre, Long> {
    List<Chapitre> findByVolumeId(Long volumeId);
}