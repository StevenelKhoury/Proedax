package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Chapitre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    private String titre;
    private int nombrePages;

    @ManyToOne
    private Volume volume;
}
