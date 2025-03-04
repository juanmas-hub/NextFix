package ar.dev.juanmabravo.nextfix.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "La nacionalidad no puede estar vacia") //validation
    @Size(min = 5, max = 30)
    private String nacionalidad;

    @NotNull(message = "La fecha de nacimiento no puede ser nula") //validation
    private LocalDate fechaNacimiento;

    @NotNull(message = "El email no puede estar en blanco") //validation
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "El formato del email no es válido")
    private String email;

    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER)
    private List<Pelicula> peliculasDirigidas = new ArrayList<>();

    @OneToOne(mappedBy = "director", cascade = CascadeType.ALL)
    private Usuario usuario;


}
