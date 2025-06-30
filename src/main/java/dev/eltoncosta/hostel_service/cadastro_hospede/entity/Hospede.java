package dev.eltoncosta.hostel_service.cadastro_hospede.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Document(collection = "hospedes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hospede {

    @Id
    private String id;
    private String nome; //nome completo
    private String cpf;
    private BigDecimal valorDaDiaria;
    @NonNull
    private Cidade cidade;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeCheckIn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeCheckOut;

    public long getTotalDias() {
            if (dataDeCheckIn != null && dataDeCheckOut != null) {
                return ChronoUnit.DAYS.between(dataDeCheckIn, dataDeCheckOut);
            }
            return 0;
    }

    @Override
    public String toString() {
        return "Hospede{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", valorDaDiaria=" + valorDaDiaria +
                ", cidade=" + cidade +
                ", totalDias=" + getTotalDias() +
                '}';
    }

}
