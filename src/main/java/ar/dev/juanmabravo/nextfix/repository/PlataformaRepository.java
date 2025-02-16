package ar.dev.juanmabravo.nextfix.repository;

import ar.dev.juanmabravo.nextfix.models.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataforma, Long> { // Clase y tipo de dato del id
}
