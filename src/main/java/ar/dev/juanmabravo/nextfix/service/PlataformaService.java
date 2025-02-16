package ar.dev.juanmabravo.nextfix.service;

import ar.dev.juanmabravo.nextfix.models.Pelicula;
import ar.dev.juanmabravo.nextfix.models.Plataforma;
import ar.dev.juanmabravo.nextfix.repository.PlataformaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PlataformaService {

    private PlataformaRepository plataformaRepository;

    public Plataforma obtenerPlataformaPorId(Long id) {
        return plataformaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontro la plataforma con id: "+id));
    }

    public List<Plataforma> listarPlataformas() {
        return plataformaRepository.findAll();
    }

    public Plataforma guardarPlataforma(Plataforma plataforma) {
        return plataformaRepository.save(plataforma);
    }

    @Transactional
    public void eliminarPlataforma(Long id) {

        Plataforma plataforma = plataformaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plataforma no encontrada"));

        // Desvincular las plataformas de las peliculas
        for(Pelicula pelicula : plataforma.getPeliculas()) {
            pelicula.getPlataformasDisponibles().remove(plataforma);
        }

        // Limpiar la lista de peliculas asociadas a la plataforma
        plataforma.getPeliculas().clear();

        // Finalmete, eliminamos la plataforma
        plataformaRepository.delete(plataforma);
    }
}
