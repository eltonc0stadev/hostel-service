package dev.eltoncosta.hostel_service.cadastro_hospede.controller;

import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Cadastro;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Convenio;
import dev.eltoncosta.hostel_service.cadastro_hospede.service.CadastroService;
import dev.eltoncosta.hostel_service.cadastro_hospede.service.ConvenioService;
import dev.eltoncosta.hostel_service.cadastro_hospede.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;
    private final CadastroService cadastroService;
    private final ConvenioService convenioService;

    @GetMapping("/cadastros")
    public ResponseEntity<byte[]> downloadRelatorioCadastros(@RequestParam("mes") Integer mes, @RequestParam("ano") Integer ano, @RequestParam("convenioid") String convenioId) throws Exception {

        if (mes == null || ano == null || convenioId == null) {
            throw new IllegalArgumentException("Parâmetros 'mes', 'ano' e 'convenioid' são obrigatórios.");
        }

        List<Cadastro> cadastros = cadastroService.listarCadastrosPorConvenioEMesEAno(convenioId, mes, ano );

        byte[] arquivo = relatorioService.gerarRelatorioCadastros(cadastros, mes, ano);
        Convenio convenio = convenioService.buscarConvenioPorId(convenioId);
        String filename = "relatorio_cadastros_" + convenio.getNome() + "_" + mes + "_" + ano;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename + ".xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(arquivo);
    }

}