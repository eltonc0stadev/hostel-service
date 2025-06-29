package dev.eltoncosta.hostel_service.cadastro_hospede.service;

import dev.eltoncosta.hostel_service.cadastro_hospede.repository.CadastroRepository;
import dev.eltoncosta.hostel_service.cadastro_hospede.repository.CidadeRepository;
import dev.eltoncosta.hostel_service.cadastro_hospede.repository.ConvenioRepository;
import dev.eltoncosta.hostel_service.cadastro_hospede.repository.HospedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroService {

    private final CadastroRepository cadastroRepository;
    private final HospedeRepository hospedeRepository;
    private final CidadeRepository cidadeRepository;
    private final ConvenioRepository convenioRepository;



}
