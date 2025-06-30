package dev.eltoncosta.hostel_service.cadastro_hospede.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cidades")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cidade {

    @Id
    private String id;
    private String cidade; //Nome da cidade, por exemplo: São Paulo, Rio de Janeiro, Belo Horizonte, etc.
    private String estado; //Sigla do estado, por exemplo: SP, RJ, MG, etc.

}
