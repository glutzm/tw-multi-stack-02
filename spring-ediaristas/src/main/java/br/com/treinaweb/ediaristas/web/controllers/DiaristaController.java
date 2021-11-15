package br.com.treinaweb.ediaristas.web.controllers;

import br.com.treinaweb.ediaristas.models.Diarista;
import br.com.treinaweb.ediaristas.services.DiaristaService;
import br.com.treinaweb.ediaristas.validators.CepValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/admin/diaristas")
public class DiaristaController {

    @Autowired
    private DiaristaService diaristaService;

    @Autowired
    private CepValidator cepValidator;

    @InitBinder("diarista")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(cepValidator);
    }

    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("admin/diaristas/listar");

        modelAndView.addObject("diaristas", diaristaService.listar());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/diaristas/form");

        modelAndView.addObject("diarista", new Diarista());

        return modelAndView;
    }

    // TODO: tratar exception ao salvar foto (cadastro e edição)
    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam MultipartFile imagem, @Valid Diarista diarista, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "admin/diaristas/form";
        }

        diaristaService.cadastrar(imagem, diarista);
        return "redirect:/admin/diaristas";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("admin/diaristas/form");

        modelAndView.addObject("diarista", diaristaService.buscarPorId(id));

        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(
            @RequestParam MultipartFile imagem,
            @PathVariable Long id,
            @Valid Diarista diarista,
            BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "admin/diaristas/form";
        }

        diaristaService.editar(imagem, id, diarista);

        return "redirect:/admin/diaristas";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        diaristaService.excluir(id);

        return "redirect:/admin/diaristas";
    }
}
