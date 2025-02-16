package ar.dev.juanmabravo.nextfix.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data // toString, getters, setter, hashcode, equals
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nacionalidad;

    private LocalDate fechaNacimiento;

    private String email;

    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER)
    private List<Pelicula> peliculasDirigidas = new ArrayList<>();


}
