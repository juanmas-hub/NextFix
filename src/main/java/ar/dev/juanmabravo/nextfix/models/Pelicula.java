package ar.dev.juanmabravo.nextfix.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;


@Entity
@Data // toString, getters, setter, hashcode, equals
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String titulo;

    private String genero;

    private LocalDate fechaEstreno;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @ToString.Exclude
    private Director director;

    @ManyToMany
    @JoinTable(name = "Pelicula_Plataforma", // nombre tabla auxiliar en bd
            joinColumns = @JoinColumn(name = "pelicula_id"), // nombre en bd
            inverseJoinColumns = @JoinColumn(name = "plataforma_id")) // nombre en bd

    @ToString.Exclude
    @Builder.Default
    private List<Plataforma> plataformasDisponibles = new ArrayList<>();//netflix, hbo, etc



}
