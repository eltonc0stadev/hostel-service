package dev.eltoncosta.hostel_service.controller;

import dev.eltoncosta.hostel_service.controller.request.FuncionarioRequest;
import dev.eltoncosta.hostel_service.entity.Funcionario;
import dev.eltoncosta.hostel_service.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }

    @GetMapping("/{id}")
    public Funcionario buscarFuncionarioPorId(@PathVariable String id) {
        return funcionarioService.buscarFuncionarioPorId(id);
    }

    @PostMapping("/registrar")
    public Funcionario registrarFuncionario(@RequestBody FuncionarioRequest funcionario) {
        return funcionarioService.registrarFuncionario(funcionario);
    }

    @DeleteMapping("/{id}")
    public void removerFuncionario(@PathVariable String id) {
        funcionarioService.removerFuncionario(id);
    }

}
