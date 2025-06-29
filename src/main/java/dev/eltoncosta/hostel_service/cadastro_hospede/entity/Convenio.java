package dev.eltoncosta.hostel_service.cadastro_hospede.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "convenio")
public class Convenio {

    @Id
    private String id;
    private String nome;

}
