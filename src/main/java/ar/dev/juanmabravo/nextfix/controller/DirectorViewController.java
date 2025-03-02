package ar.dev.juanmabravo.nextfix.controller;

import ar.dev.juanmabravo.nextfix.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class DirectorViewController {

    private final DirectorService directorService;

    @GetMapping("/directores") // Endpoint
    public String listarDirectores(Model model)
    {
        // "directores" es el nombre de la variable que en el front va a contener la lista
        model.addAttribute("directores", directorService.listarDirectores());

        // Retornamos el nombre del html (vista) que va a mostrar la lista
        return "listaDirectores";
    }
}
