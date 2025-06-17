package com.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String nationalite;

    @OneToMany(mappedBy = "auteur")
    private List<Livre> livres = new ArrayList<>();
}