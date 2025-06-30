package dev.eltoncosta.hostel_service.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "funcionarios")
@Data
@Builder
public class Funcionario {

    @Id
    private String id;
    private String nome;
    private String cpf;
    private Cargo cargo;
    private String telefone;

}
