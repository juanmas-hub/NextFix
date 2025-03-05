package ar.dev.juanmabravo.nextfix.controller;

import ar.dev.juanmabravo.nextfix.service.CustomUserDetailsService;
import ar.dev.juanmabravo.nextfix.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class DirectorViewController {

    private final DirectorService directorService;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/directores")
    public String listarDirectores(Model model) {
        model.addAttribute("directores", directorService.listarDirectores());
        model.addAttribute("userService", customUserDetailsService);

        return "listaDirectores";
    }
}
