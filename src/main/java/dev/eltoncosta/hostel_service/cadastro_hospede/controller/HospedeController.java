package dev.eltoncosta.hostel_service.cadastro_hospede.controller;

import dev.eltoncosta.hostel_service.cadastro_hospede.controller.request.HospedeRequest;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Hospede;
import dev.eltoncosta.hostel_service.cadastro_hospede.service.CidadeService;
import dev.eltoncosta.hostel_service.cadastro_hospede.service.HospedeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/hospedes")
@RequiredArgsConstructor
public class HospedeController {

    private final CidadeService cidadeService;
    private final HospedeService hospedeService;

    @PostMapping("/registrar")
    public Hospede registrarHospede(@RequestBody HospedeRequest request) {

        Hospede hospede = Hospede.builder()
                .nome(request.nome())
                .cpf(request.cpf())
                .valorDaDiaria(request.valorDaDiaria())
                .cidade(cidadeService.buscarCidadePorId(request.cidadeId()))
                .dataDeCheckIn(LocalDate.parse(request.dataDeCheckIn(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .dataDeCheckOut(LocalDate.parse(request.dataDeCheckOut(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
        return hospedeService.registrarHospede(hospede);

    }

    @GetMapping
    public List<Hospede> listarHospedes() {
        return hospedeService.listarHospedes();
    }

    @GetMapping("/{id}")
    public Hospede buscarHospedePorId(@PathVariable String id) {
        return hospedeService.buscarHospedePorId(id);
    }

}
