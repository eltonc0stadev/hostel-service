package dev.eltoncosta.hostel_service.cadastro_hospede.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.eltoncosta.hostel_service.entity.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Document(collection = "cadastros")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cadastro {

    @Id
    private String id;
    private List<Hospede> hospedes;
    private Funcionario funcionario;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Convenio convenio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeChegada;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeSaida;
    private Long totalDias;
    private Status status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MetodoDePagamento metodoDePagamento;
    private BigDecimal valorTotalDiarias;

    public void calcularValorTotalDiarias() {
        this.valorTotalDiarias = hospedes.stream()
                .map(hospede -> hospede.getValorDaDiaria().multiply(BigDecimal.valueOf(hospede.getTotalDias())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getTotalDias() {
        if (dataDeChegada != null && dataDeSaida != null) {
            this.totalDias = ChronoUnit.DAYS.between(dataDeChegada, dataDeSaida);
        } else {
            this.totalDias = 0L;
        }
        return this.totalDias;
    }

    @Override
    public String toString() {
        return "Cadastro{" +
                "id='" + id + '\'' +
                ", hospedes=" + hospedes +
                ", funcionario=" + funcionario +
                ", convenio=" + convenio +
                ", dataDeChegada=" + dataDeChegada +
                ", dataDeSaida=" + dataDeSaida +
                ", totalDias=" + totalDias +
                ", status=" + status +
                ", metodoDePagamento=" + metodoDePagamento +
                ", valorTotalDiarias=" + valorTotalDiarias +
                '}';
    }

}
