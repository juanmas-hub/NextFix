package ar.dev.juanmabravo.nextfix.repository;

import ar.dev.juanmabravo.nextfix.models.Director;
import ar.dev.juanmabravo.nextfix.models.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    Optional<Pelicula> findByGenero(String genero);

    // Tabla con el nombre de la entidad
    @Query("SELECT p FROM Pelicula p ORDER BY LOWER(p.titulo) ASC")
    List<Pelicula> findByAllByOrderByTituloIgnoreCaseAsc();

    void deleteByDirector(Director diector);

    void deleteByGenero(String genero);
}
