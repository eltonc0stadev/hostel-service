package dev.eltoncosta.hostel_service.cadastro_hospede.service;

import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Hospede;
import dev.eltoncosta.hostel_service.cadastro_hospede.repository.CidadeRepository;
import dev.eltoncosta.hostel_service.cadastro_hospede.repository.HospedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospedeService {

    private final HospedeRepository hospedeRepository;
    private final CidadeRepository cidadeRepository;

    public Hospede cadastrarHospede(Hospede hospede) {
        if (hospede.getNome() == null || hospede.getCpf() == null || hospede.getCidade() == null || hospede.getValorDaDiaria() == null) {
            throw new IllegalArgumentException("Campos obrigatórios não podem ser nulos");
        }
        if (hospede.getDataDeCheckIn() == null || hospede.getDataDeCheckOut() == null) {
            throw new IllegalArgumentException("Data de Check-In e Check-Out não podem ser nulas");
        }
        if (!cidadeRepository.existsById(hospede.getCidade().getId())) {
            throw new IllegalArgumentException("Cidade não encontrada");
        }
        return hospedeRepository.save(hospede);
    }

    public Hospede buscarHospedePorCpf(String cpf) {
        Optional<Hospede> optionalHospede = hospedeRepository.findByCpf(cpf);
        if (optionalHospede.isEmpty()) {
            throw new IllegalArgumentException("Hospede não encontrado com o CPF: " + cpf);
        }
        return optionalHospede.get();
    }

    public void removerHospede(String id) {
        if (!hospedeRepository.existsById(id)) {
            throw new IllegalArgumentException("Hospede não encontrado");
        }
        hospedeRepository.deleteById(id);
    }

    public List<Hospede> listarHospedes() {
        List<Hospede> hospedes = hospedeRepository.findAll();
        if (hospedes.isEmpty()) {
            throw new IllegalArgumentException("Nenhum hóspede cadastrado");
        }
        return hospedes;
    }

    public Hospede atualizarHospede(String id, Hospede hospedeAtualizado) {
        if (!hospedeRepository.existsById(id)) {
            throw new IllegalArgumentException("Hospede não encontrado");
        }
        Optional<Hospede> hospedeOptional = hospedeRepository.findById(id);
        if (hospedeOptional.isEmpty()) {
            throw new IllegalArgumentException("Hospede não encontrado com o ID: " + id);
        }
        hospedeAtualizado.setNome(hospedeAtualizado.getNome() == null ? hospedeOptional.get().getNome() : hospedeAtualizado.getNome());
        hospedeAtualizado.setCpf(hospedeAtualizado.getCpf() == null ? hospedeOptional.get().getCpf() : hospedeAtualizado.getCpf());
        hospedeAtualizado.setCidade(hospedeAtualizado.getCidade() == null ? hospedeOptional.get().getCidade() : hospedeAtualizado.getCidade());
        hospedeAtualizado.setValorDaDiaria(hospedeAtualizado.getValorDaDiaria() == null ? hospedeOptional.get().getValorDaDiaria() : hospedeAtualizado.getValorDaDiaria());
        hospedeAtualizado.setDataDeCheckIn(hospedeAtualizado.getDataDeCheckIn() == null ? hospedeOptional.get().getDataDeCheckIn() : hospedeAtualizado.getDataDeCheckIn());
        hospedeAtualizado.setDataDeCheckOut(hospedeAtualizado.getDataDeCheckOut() == null ? hospedeOptional.get().getDataDeCheckOut() : hospedeAtualizado.getDataDeCheckOut());
        hospedeAtualizado.setId(id);
        return hospedeRepository.save(hospedeAtualizado);
    }

    public Hospede atualizarHospedeNome(String id, String nome) {
        return this.atualizarHospede(id, Hospede.builder().nome(nome).build());
    }

    public Hospede atualizarHospedeCpf(String id, String cpf) {
        return this.atualizarHospede(id, Hospede.builder().cpf(cpf).build());
    }

    public Hospede atualizarHospedeCidade(String id, String cidadeNome) {
        return this.atualizarHospede(id, Hospede.builder().cidade(cidadeRepository.findByName(cidadeNome)
                .orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada"))).build());
    }

    public Hospede atualizarHospedeValorDaDiaria(String id, BigDecimal valorDaDiaria) {
        return this.atualizarHospede(id, Hospede.builder().valorDaDiaria(valorDaDiaria).build());
    }

    public Hospede atualizarHospedeDataDeCheckIn(String id, String dataDeCheckIn) {
        return this.atualizarHospede(id, Hospede.builder().dataDeCheckIn(LocalDate.parse(dataDeCheckIn, DateTimeFormatter.ofPattern("dd/MM/yyyy"))).build());
    }

    public Hospede atualizarHospedeDataDeCheckOut(String id, String dataDeCheckOut) {
        return this.atualizarHospede(id, Hospede.builder().dataDeCheckOut(LocalDate.parse(dataDeCheckOut, DateTimeFormatter.ofPattern("dd/MM/yyyy"))).build());
    }

    public void deletarHospede(String id) {
        if (!hospedeRepository.existsById(id)) {
            throw new IllegalArgumentException("Hospede não encontrado");
        }
        hospedeRepository.deleteById(id);
    }

}
