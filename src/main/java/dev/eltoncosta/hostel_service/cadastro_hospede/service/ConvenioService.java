package dev.eltoncosta.hostel_service.cadastro_hospede.service;

import dev.eltoncosta.hostel_service.cadastro_hospede.controller.request.ConvenioRequest;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Convenio;
import dev.eltoncosta.hostel_service.cadastro_hospede.repository.ConvenioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvenioService {

    private final ConvenioRepository convenioRepository;

    public Convenio buscarConvenioPorId(String id) {
        return convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convenio não encontrado com o ID: " + id));
    }

    public Convenio registrarConvenio(ConvenioRequest convenio) {
        if (convenio.nome() == null) {
            throw new IllegalArgumentException("Campos obrigatórios não podem ser nulos");
        }
        Convenio novoConvenio = Convenio.builder().nome(convenio.nome()).build();
        return convenioRepository.save(novoConvenio);
    }

    public void removerConvenio(String id) {
        if (!convenioRepository.existsById(id)) {
            throw new RuntimeException("Convenio não encontrado com o ID: " + id);
        }
        convenioRepository.deleteById(id);
    }

    public List<Convenio> listarConvenios() {
        List<Convenio> convenios = convenioRepository.findAll();
        if (convenios.isEmpty()) {
            throw new RuntimeException("Nenhum convênio cadastrado");
        }
        return convenios;
    }

}
