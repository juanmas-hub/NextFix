package ar.dev.juanmabravo.nextfix.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre de la plataforma no puede estar en blanco") //validation
    @Size(min = 1, max = 30)
    private String nombre;

    @NotNull(message = "El precio no puede ser nulo")
    private BigDecimal precio;

    @NotBlank(message = "La moneda de la plataforma no puede estar en blanco") //validation
    @Size(min = 1, max = 30)
    private String moneda;

    @NotBlank(message = "El enlace de la plataforma no puede estar en blanco") //validation
    @Size(min = 1, max = 80)
    private String enlace;

    // Nombre del atributo a mappear
    @ManyToMany(mappedBy = "plataformasDisponibles", fetch = FetchType.EAGER)
    private List<Pelicula> peliculas = new ArrayList<>();
}
