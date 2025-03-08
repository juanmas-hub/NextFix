package ar.dev.juanmabravo.nextfix;


import ar.dev.juanmabravo.nextfix.models.Plataforma;
import ar.dev.juanmabravo.nextfix.service.PlataformaService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired)) // Inyección de dependencia mediante constructor
public class PlataformaServiceTest extends BaseTest {

    private final PlataformaService plataformaService;
    private Plataforma plataformaGuardada;

    @BeforeEach
    void setup() {
        Plataforma plataforma = new Plataforma();
        plataforma.setNombre("NetflixTest");
        plataforma.setPrecio(new BigDecimal("5"));
        plataforma.setMoneda("ars");
        plataforma.setEnlace("www.netflix.com");

        plataformaGuardada = plataformaService.guardarPlataforma(plataforma);
    }

    @Test
    void testGuardarPlatafoma()
    {
        assertNotNull(plataformaGuardada.getId());
        assertEquals("NetflixTest", plataformaGuardada.getNombre());
        assertEquals(new BigDecimal("5"), plataformaGuardada.getPrecio());
        assertEquals("ars", plataformaGuardada.getMoneda());
        assertEquals("www.netflix.com", plataformaGuardada.getEnlace());
    }

    @Test
    void testListarPlataformas()
    {
        assertFalse(plataformaService.listarPlataformas().isEmpty());
    }

    @Test
    void testObtenerPlataformaPorId()
    {
        Long idPlataforma = 1L;

        Plataforma plataforma = plataformaService.obtenerPlataformaPorId(idPlataforma);

        assertNotNull(plataforma);
        assertEquals(idPlataforma, plataforma.getId());
    }

    @Test
    void testEliminarPlataforma()
    {
        List<Plataforma> plataformaList = plataformaService.listarPlataformas();
        assertFalse(plataformaList.isEmpty());
        Long idPlataformaAEliminar = plataformaList.get(plataformaList.size()-1).getId();

        plataformaService.eliminarPlataforma(idPlataformaAEliminar);

        Exception exception = assertThrows(RuntimeException.class, () ->
                plataformaService.obtenerPlataformaPorId(idPlataformaAEliminar));

        String mensajeEsperado = "No se encontro la plataforma con el id: " + idPlataformaAEliminar;
        String mensajeActual = exception.getMessage();

        assertTrue(mensajeEsperado.contains(mensajeActual));
    }


}
