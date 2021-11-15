package br.com.treinaweb.ediaristas.api.controllers;

import br.com.treinaweb.ediaristas.dtos.DiaristasPagedResponse;
import br.com.treinaweb.ediaristas.services.DiaristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diaristas-cidade")
public class DiaristaRestController {

    @Autowired
    private DiaristaService diaristaService;

    @GetMapping
    public DiaristasPagedResponse buscarDiaristasPorCep(@RequestParam String cep) {
        return diaristaService.buscarDiaristasPorCep(cep);
    }
}
