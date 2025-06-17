package com.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;

    @ManyToOne
    private Auteur auteur;

    @ManyToMany
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Volume> volumes = new ArrayList<>();
}

