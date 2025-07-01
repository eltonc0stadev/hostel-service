package dev.eltoncosta.hostel_service.cadastro_hospede.controller;

import dev.eltoncosta.hostel_service.cadastro_hospede.controller.request.CadastroRequest;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Cadastro;
import dev.eltoncosta.hostel_service.cadastro_hospede.service.CadastroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastros")
@RequiredArgsConstructor
public class CadastroController {

    private final CadastroService cadastroService;

    @GetMapping
    public List<Cadastro> buscarTodosCadastros() {
        return cadastroService.listarCadastros();
    }

    @PostMapping("/registrar")
    public Cadastro registrarCadastro(@RequestBody CadastroRequest request) {
        return cadastroService.registrarCadastro(request);
    }

    @GetMapping("/{id}")
    public Cadastro buscarCadastroPorId(@PathVariable String id) {
        return cadastroService.buscarCadastroPorId(id);
    }

    @DeleteMapping("/{id}")
    public void removerCadastroPorId(@PathVariable String id) {
        cadastroService.removerCadastro(id);
    }

    @GetMapping("/consulta")
    public List<Cadastro> consultarPorMesEDia(
            @RequestParam("mes") Integer mes,
            @RequestParam("ano") Integer ano,
            @RequestParam("convenioid") String convenioId) {
        if (mes == null || ano == null || convenioId == null) {
            throw new IllegalArgumentException("Parâmetros 'mes', 'ano' e 'convenioid' são obrigatórios.");
        }
        return cadastroService.listarCadastrosPorConvenioEMesEAno(convenioId, mes, ano);
    }

}
