package com.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Volume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    private LocalDate datePublication;

    @ManyToOne
    private Livre livre;

    @OneToMany(mappedBy = "volume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapitre> chapitres = new ArrayList<>();
}