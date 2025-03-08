package ar.dev.juanmabravo.nextfix;


import ar.dev.juanmabravo.nextfix.models.*;
import ar.dev.juanmabravo.nextfix.service.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired)) // Inyección de dependencia mediante constructor
class PeliculaServiceTest extends BaseTest {
    private final PeliculaService peliculaService;
    private final DirectorService directorService;
    private final PlataformaService plataformaService;

    private Pelicula peliculaTemp;
    private Director directorGuardado;
    private Plataforma plataformaGuardada;

    @BeforeEach
    void setup() {
        Director director = new Director();
        director.setNacionalidad("Argentina");
        director.setFechaNacimiento(LocalDate.now());
        director.setEmail("testStanleyKubrick@example.com");

        directorGuardado = directorService.guardarDirector(director);

        Plataforma plataforma = new Plataforma();
        plataforma.setNombre("NetflixTest");
        plataforma.setPrecio(new BigDecimal("5"));
        plataforma.setMoneda("ars");
        plataforma.setEnlace("www.netflix.com");

        plataformaGuardada = plataformaService.guardarPlataforma(plataforma);

        peliculaTemp = new Pelicula();
        peliculaTemp.setTitulo("La naranja mecanica");
        peliculaTemp.setGenero("Drama");
        peliculaTemp.setFechaEstreno(LocalDate.now());
    }

    @Test
    void testGuardarPeliculaSinPlataforma()
    {
        peliculaTemp.setDirector(directorGuardado);
        Pelicula peliculaGuardada = peliculaService.guardarPelicula(
                peliculaTemp, directorGuardado.getId(), null);

        assertNotNull(peliculaGuardada.getId());
        assertEquals("La naranja mecanica", peliculaGuardada.getTitulo());
        assertEquals("Drama", peliculaGuardada.getGenero());
        assertEquals(LocalDate.now(), peliculaGuardada.getFechaEstreno());
        assertEquals(directorGuardado.getId(), peliculaGuardada.getDirector().getId());
    }

    @Test
    void testGuardarPeliculaConPlataforma()
    {
        List<Long> idsPlataformasDisponibles = new ArrayList<>();
        idsPlataformasDisponibles.add(plataformaGuardada.getId());

        Pelicula peliculaGuardada = peliculaService.guardarPelicula(
                peliculaTemp, directorGuardado.getId(), idsPlataformasDisponibles);

        assertNotNull(peliculaGuardada.getId());
        assertEquals("La naranja mecanica", peliculaGuardada.getTitulo());
        assertEquals("Drama", peliculaGuardada.getGenero());
        assertEquals(LocalDate.now(), peliculaGuardada.getFechaEstreno());
        assertEquals(directorGuardado.getId(), peliculaGuardada.getDirector().getId());
        assertFalse(peliculaGuardada.getPlataformasDisponibles().isEmpty());
    }

    @Test
    void testListarPeliculas()
    {
        List<Pelicula> peliculas = peliculaService.listarPeliculas();
        assertFalse(peliculas.isEmpty());
    }
}
