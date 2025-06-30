package dev.eltoncosta.hostel_service.controller.request;

import dev.eltoncosta.hostel_service.entity.Cargo;

public record FuncionarioRequest(String nome, String cpf, Cargo cargo, String telefone) {
}
