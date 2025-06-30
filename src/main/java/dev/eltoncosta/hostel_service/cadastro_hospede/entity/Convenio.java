package dev.eltoncosta.hostel_service.cadastro_hospede.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "convenios")
@Builder
@Data
public class Convenio {

    @Id
    private String id;
    private String nome;

}
