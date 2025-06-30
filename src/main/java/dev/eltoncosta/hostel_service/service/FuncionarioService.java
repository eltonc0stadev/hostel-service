package dev.eltoncosta.hostel_service.service;

import dev.eltoncosta.hostel_service.controller.request.FuncionarioRequest;
import dev.eltoncosta.hostel_service.entity.Funcionario;
import dev.eltoncosta.hostel_service.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public Funcionario registrarFuncionario(FuncionarioRequest funcionario) {
        if (funcionario.nome() == null || funcionario.nome().isEmpty()) {
            throw new IllegalArgumentException("O nome do funcionário não pode ser vazio.");
        }
        if (funcionario.cpf() == null || funcionario.cpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF do funcionário não pode ser vazio.");
        }
        if (funcionario.cargo() == null) {
            throw new IllegalArgumentException("O cargo do funcionário não pode ser vazio.");
        }
        Funcionario novoFuncionario = Funcionario.builder()
                .nome(funcionario.nome())
                .cpf(funcionario.cpf())
                .cargo(funcionario.cargo())
                .telefone(funcionario.telefone())
                .build();
        return funcionarioRepository.save(novoFuncionario);
    }

    public Funcionario buscarFuncionarioPorId(@PathVariable String id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado com o ID: " + id));
    }

    public void removerFuncionario(String id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Funcionário não encontrado com o ID: " + id);
        }
        funcionarioRepository.deleteById(id);
    }

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        if (funcionarios.isEmpty()) {
            throw new IllegalArgumentException("Nenhum funcionário cadastrado.");
        }
        return funcionarios;
    }

}
