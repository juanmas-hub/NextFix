package ar.dev.juanmabravo.nextfix;

import ar.dev.juanmabravo.nextfix.models.Director;
import ar.dev.juanmabravo.nextfix.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired)) // Inyección de dependencia mediante constructor
public class DirectorServiceTest extends BaseTest{

    private final DirectorService directorService;
    private Director directorGuardado;

    @BeforeEach
    void setup() {
        Director director = new Director();
        director.setNacionalidad("Argentina");
        director.setFechaNacimiento(LocalDate.now());
        director.setEmail("testStanleyKubrick@example.com");

        directorGuardado = directorService.guardarDirector(director);
    }

    @Test
    void testGuardarDirector()
    {
        assertNotNull(directorGuardado.getId());
        assertEquals("Argentina", directorGuardado.getNacionalidad());
        assertEquals(LocalDate.now(), directorGuardado.getFechaNacimiento());
        assertEquals("testStanleyKubrick@example.com", directorGuardado.getEmail());
    }

    @Test
    void testListarDirectores()
    {
        assertFalse(directorService.listarDirectores().isEmpty());
    }

    @Test
    void testObtenerDirectorPorId()
    {
        Long idDirector = 1L;
        Director director = directorService.obtenerDirectorPorId(idDirector);

        assertNotNull(director);
        assertEquals(idDirector, director.getId());
    }
}
