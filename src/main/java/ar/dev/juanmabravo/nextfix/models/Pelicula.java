package ar.dev.juanmabravo.nextfix.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "El titulo de la pelicula no puede estar en blanco") //validation
    @Size(min = 1, max = 50)
    private String titulo;

    @NotBlank(message = "El genero no puede estar en blanco") //validation
    @Size(min = 5, max = 30)
    private String genero;

    @NotNull(message = "La fecha de estreno no puede ser nula") //validation
    private LocalDate fechaEstreno;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @ToString.Exclude
    @NotNull(message = "El director no puede ser nulo")
    private Director director;

    @ManyToMany
    @JoinTable(name = "Pelicula_Plataforma", // nombre tabla auxiliar en bd
            joinColumns = @JoinColumn(name = "pelicula_id"), // nombre en bd
            inverseJoinColumns = @JoinColumn(name = "plataforma_id")) // nombre en bd
    @ToString.Exclude
    @Builder.Default
    private List<Plataforma> plataformasDisponibles = new ArrayList<>();//netflix, hbo, etc



}
