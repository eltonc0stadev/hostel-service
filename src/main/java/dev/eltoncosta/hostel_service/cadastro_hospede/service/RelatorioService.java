package dev.eltoncosta.hostel_service.cadastro_hospede.service;

import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Cadastro;
import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Hospede;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    public byte[] gerarRelatorioCadastros(List<Cadastro> cadastros, int mes, int ano) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Relatório de Hospedes");
            int rowIdx = 0;

            // Estilos
            CellStyle headerStyle = createCellStyle(workbook, true, (short) 14, HorizontalAlignment.CENTER, false);
            CellStyle tableHeaderStyle = createCellStyle(workbook, true, (short) 11, HorizontalAlignment.CENTER, true);
            CellStyle tableCellStyle = createCellStyle(workbook, false, (short) 11, HorizontalAlignment.CENTER, true);
            CellStyle totalLabelStyle = createCellStyle(workbook, true, (short) 11, HorizontalAlignment.RIGHT, true);
            CellStyle totalCellStyle = createCellStyle(workbook, true, (short) 11, HorizontalAlignment.CENTER, true);

            // Cabeçalho superior
            String[] headerLines = {
                    "PHOENIX HOSTEL - Casa de Apoio",
                    "Av. José Bonifácio, nº 1636 - entre Caripunas e Paes de Souza - Bairro: Guamá",
                    "Contatos: 091982221910 - enfgisele103120@gmail.com",
                    "Relatório do mês e ano de " + mes + "/" + ano,
            };

            for (String line : headerLines) {
                Row headerRow = sheet.createRow(rowIdx++);
                Cell cell = headerRow.createCell(0);
                cell.setCellValue(line);
                cell.setCellStyle(headerStyle);
                sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(
                        rowIdx - 1, rowIdx - 1, 0, 6));
            }

            rowIdx++; // linha em branco

            // Cabeçalho tabela
            String[] columns = {
                    "HOSPEDE/ACOMPANHANTE", "ENTRADA", "SAIDA", "TOTAL DE DIARIAS",
                    "VALOR DA DIARIA", "VALOR TOTAL (DA ESTADIA DESSE HOSPEDE)", "OBSERVAÇÕES"
            };
            Row tableHeader = sheet.createRow(rowIdx++);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = tableHeader.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(tableHeaderStyle);
            }

            // Dados
            BigDecimal totalGeral = BigDecimal.ZERO;
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Cadastro cadastro : cadastros) {
                for (Hospede hospede : cadastro.getHospedes()) {
                    Row row = sheet.createRow(rowIdx++);
                    row.createCell(0).setCellValue(hospede.getNome());
                    row.getCell(0).setCellStyle(tableCellStyle);

                    row.createCell(1).setCellValue(hospede.getDataDeCheckIn() != null ? hospede.getDataDeCheckIn().format(fmt) : "");
                    row.getCell(1).setCellStyle(tableCellStyle);

                    row.createCell(2).setCellValue(hospede.getDataDeCheckOut() != null ? hospede.getDataDeCheckOut().format(fmt) : "");
                    row.getCell(2).setCellStyle(tableCellStyle);

                    long dias = hospede.getTotalDias();
                    row.createCell(3).setCellValue(dias);
                    row.getCell(3).setCellStyle(tableCellStyle);

                    BigDecimal diaria = hospede.getValorDaDiaria() != null ? hospede.getValorDaDiaria() : BigDecimal.ZERO;
                    row.createCell(4).setCellValue(diaria.toString());
                    row.getCell(4).setCellStyle(tableCellStyle);

                    BigDecimal valorTotal = diaria.multiply(BigDecimal.valueOf(dias));
                    row.createCell(5).setCellValue(valorTotal.toString());
                    row.getCell(5).setCellStyle(tableCellStyle);

                    totalGeral = totalGeral.add(valorTotal);

                    row.createCell(6).setCellValue("");
                    row.getCell(6).setCellStyle(tableCellStyle);
                }
            }

            // Linha de total geral
            Row totalRow = sheet.createRow(rowIdx++);
            for (int i = 0; i < 4; i++) {
                totalRow.createCell(i).setCellStyle(tableCellStyle);
            }
            Cell labelCell = totalRow.createCell(4);
            labelCell.setCellValue("TOTAL GERAL:");
            labelCell.setCellStyle(totalLabelStyle);

            Cell totalCell = totalRow.createCell(5);
            totalCell.setCellValue(totalGeral.toString());
            totalCell.setCellStyle(totalCellStyle);

            totalRow.createCell(6).setCellStyle(tableCellStyle);

            // Ajusta largura
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(bos);
            return bos.toByteArray();
        }
    }

    private CellStyle createCellStyle(Workbook workbook, boolean bold, short fontHeight, HorizontalAlignment alignment, boolean border) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(bold);
        font.setFontHeightInPoints(fontHeight);
        style.setFont(font);
        style.setAlignment(alignment);
        if (border) {
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
        }
        return style;
    }
}