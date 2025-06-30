package dev.eltoncosta.hostel_service.cadastro_hospede.controller.request;

import java.math.BigDecimal;
import java.util.List;

public record CadastroRequest(List<String> hospedesIds, String funcionarioId, String convenioId, String dataDeChegada, String dataDeSaida, String status, String metodoDePagamento) {
}
