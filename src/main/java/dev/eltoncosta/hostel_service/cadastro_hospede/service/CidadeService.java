package dev.eltoncosta.hostel_service.cadastro_hospede.service;

import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Cidade;
import dev.eltoncosta.hostel_service.cadastro_hospede.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public Cidade cadastrarCidade(Cidade cidade) {
        if (cidadeRepository.existsByCidade(cidade.getCidade())) {
            throw new IllegalArgumentException("Cidade já cadastrada");
        }
        if (cidade.getCidade() == null || cidade.getEstado() == null) {
            throw new IllegalArgumentException("Cidade e Estado não podem ser nulos");
        }
        return cidadeRepository.save(cidade);
    }

    public List<Cidade> listarCidades() {
        return cidadeRepository.findAll();
    }

    public void removerCidade(String id) {
        if (!cidadeRepository.existsById(id)) {
            throw new IllegalArgumentException("Cidade não encontrada");
        }
        cidadeRepository.deleteById(id);
    }

}
