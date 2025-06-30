package dev.eltoncosta.hostel_service.cadastro_hospede.controller;

import dev.eltoncosta.hostel_service.cadastro_hospede.controller.request.CidadeRequest;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Cidade;
import dev.eltoncosta.hostel_service.cadastro_hospede.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeService cidadeService;

    @PostMapping("/registrar")
    public Cidade registrarCidade(@RequestBody CidadeRequest request) {
        return cidadeService.registrarCidade(request);
    }

    @GetMapping
    public List<Cidade> listarCidades() {
        return cidadeService.listarCidades();
    }

}
