package dev.eltoncosta.hostel_service.cadastro_hospede.service;

import dev.eltoncosta.hostel_service.cadastro_hospede.controller.request.CidadeRequest;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Cidade;
import dev.eltoncosta.hostel_service.cadastro_hospede.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public Cidade registrarCidade(CidadeRequest cidade) {
        if (cidadeRepository.existsByCidade(cidade.cidade())) {
            throw new IllegalArgumentException("Cidade já cadastrada");
        }
        if (cidade.cidade() == null || cidade.estado() == null) {
            throw new IllegalArgumentException("Cidade e Estado não podem ser nulos");
        }
        Cidade novaCidade = Cidade.builder()
                .cidade(cidade.cidade())
                .estado(cidade.estado())
                .build();
        return cidadeRepository.save(novaCidade);
    }

    public List<Cidade> listarCidades() {
        return cidadeRepository.findAll();
    }

    public Cidade buscarCidadePorId(String id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada"));
    }

    public void removerCidade(String id) {
        if (!cidadeRepository.existsById(id)) {
            throw new IllegalArgumentException("Cidade não encontrada");
        }
        cidadeRepository.deleteById(id);
    }

}
