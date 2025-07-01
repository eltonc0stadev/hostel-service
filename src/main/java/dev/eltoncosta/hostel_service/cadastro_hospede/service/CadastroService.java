package dev.eltoncosta.hostel_service.cadastro_hospede.service;

import dev.eltoncosta.hostel_service.cadastro_hospede.controller.request.CadastroRequest;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Cadastro;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Convenio;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Hospede;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Status;
import dev.eltoncosta.hostel_service.cadastro_hospede.repository.CadastroRepository;
import dev.eltoncosta.hostel_service.entity.Funcionario;
import dev.eltoncosta.hostel_service.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroService {

    private final CadastroRepository cadastroRepository;
    private final HospedeService hospedeService;
    private final FuncionarioService funcionarioService;
    private final ConvenioService convenioService;

    public Cadastro registrarCadastro(CadastroRequest cadastro) {

        List<Hospede> hospedes = new ArrayList<>();
        cadastro.hospedesIds().forEach(hospedesId -> {
            Hospede hospede = hospedeService.buscarHospedePorId(hospedesId);
            hospedes.add(hospede);
        });

        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(cadastro.funcionarioId());
        Convenio convenio;
        if (cadastro.convenioId() != null) {
            convenio = convenioService.buscarConvenioPorId(cadastro.convenioId());
        } else {
            convenio = null;
        }

        Cadastro novoCadastro = Cadastro.builder()
                .hospedes(hospedes)
                .funcionario(funcionario)
                .convenio(convenio)
                .dataDeChegada(LocalDate.parse(cadastro.dataDeChegada(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
        novoCadastro.calcularValorTotalDiarias();
        novoCadastro.setStatus(novoCadastro.getConvenio() != null ? Status.CONVENIO : Status.PENDENTE);

        return cadastroRepository.save(novoCadastro);
    }

    public Cadastro buscarCadastroPorId(String id) {
        return cadastroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cadastro não encontrado com o ID: " + id));
    }

    public List<Cadastro> listarCadastros() {
        List<Cadastro> cadastros = cadastroRepository.findAll();
        if (cadastros.isEmpty()) {
            throw new IllegalArgumentException("Nenhum cadastro encontrado");
        }
        return cadastros;
    }

    public void removerCadastro(String id) {
        if (!cadastroRepository.existsById(id)) {
            throw new IllegalArgumentException("Cadastro não encontrado");
        }
        cadastroRepository.deleteById(id);
    }

    public List<Cadastro> listarCadastrosPorConvenioEMesEAno(String convenioId, int mes, int ano) {
        return cadastroRepository.findAll().stream()
                .filter(cadastro -> cadastro.getConvenio() != null
                        && cadastro.getConvenio().getId().equals(convenioId)
                        && cadastro.getStatus() != Status.CANCELADO
                        && cadastro.getDataDeChegada() != null
                        && cadastro.getDataDeChegada().getMonthValue() == mes
                        && cadastro.getDataDeChegada().getYear() == ano)
                .toList();
    }

}
