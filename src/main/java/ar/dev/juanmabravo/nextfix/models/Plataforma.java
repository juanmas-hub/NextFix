package ar.dev.juanmabravo.nextfix.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data // toString, getters, setter, hashcode, equals
@NoArgsConstructor
@AllArgsConstructor
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;

    private BigDecimal precio;

    private String moneda;

    private String enlace;

    // Nombre del atributo a mappear
    @ManyToMany(mappedBy = "plataformasDisponibles", fetch = FetchType.EAGER)
    private List<Pelicula> peliculas = new ArrayList<>();
}
