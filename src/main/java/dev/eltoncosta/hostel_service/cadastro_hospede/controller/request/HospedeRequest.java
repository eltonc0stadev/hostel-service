package dev.eltoncosta.hostel_service.cadastro_hospede.controller.request;

import java.math.BigDecimal;

public record HospedeRequest(String nome, String cpf, BigDecimal valorDaDiaria, String cidadeId, String dataDeCheckIn, String dataDeCheckOut) {
}
