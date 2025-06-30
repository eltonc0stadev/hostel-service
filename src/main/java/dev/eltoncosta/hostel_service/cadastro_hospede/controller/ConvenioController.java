package dev.eltoncosta.hostel_service.cadastro_hospede.controller;

import dev.eltoncosta.hostel_service.cadastro_hospede.controller.request.ConvenioRequest;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Convenio;
import dev.eltoncosta.hostel_service.cadastro_hospede.service.ConvenioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convenios")
@RequiredArgsConstructor
public class ConvenioController {

    private final ConvenioService convenioService;

    @GetMapping
    public List<Convenio> listarConvenios() {
        List<Convenio> convenios = convenioService.listarConvenios();
        if (convenios.isEmpty()) {
            throw new RuntimeException("Nenhum convênio cadastrado");
        }
        return convenios;
    }

    @GetMapping("/{id}")
    public Convenio buscarConvenioPorId(String id) {
        return convenioService.buscarConvenioPorId(id);
    }

    @PostMapping("/registrar")
    public Convenio registrarConvenio(@RequestBody ConvenioRequest request) {
        return convenioService.registrarConvenio(request);
    }

    @DeleteMapping("/{id}")
    public void removerConvenio(@PathVariable String id) {
        convenioService.removerConvenio(id);
    }



}
