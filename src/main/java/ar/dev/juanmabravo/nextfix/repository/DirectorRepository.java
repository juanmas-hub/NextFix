package ar.dev.juanmabravo.nextfix.repository;

import ar.dev.juanmabravo.nextfix.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
