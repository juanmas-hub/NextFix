package ar.dev.juanmabravo.nextfix.service;

import ar.dev.juanmabravo.nextfix.models.Director;
import ar.dev.juanmabravo.nextfix.repository.DirectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor // Usamos inyección de dependencias por constructor
public class DirectorService {

    private DirectorRepository directorRepository;

    public Director obtenerDirectorPorId(Long id) {
        return directorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontro el director con id: " + id)
        );
    }

    public List<Director> listarDirectores() {
        return directorRepository.findAll();
    }

    public Director guardarDirector(Director director) {
        return directorRepository.save(director);
    }

}
