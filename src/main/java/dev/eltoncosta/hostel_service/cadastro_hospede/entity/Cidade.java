package dev.eltoncosta.hostel_service.cadastro_hospede.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cidade")
@Data
@Builder
public class Cidade {

    @Id
    private String id;
    private String Cidade; //Nome da cidade, por exemplo: São Paulo, Rio de Janeiro, Belo Horizonte, etc.
    private String Estado; //Sigla do estado, por exemplo: SP, RJ, MG, etc.

}
