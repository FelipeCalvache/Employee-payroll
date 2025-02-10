package com.mgmingenieriayproyectos.nomina_empleados.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Service
public class ExcelService {

  public ByteArrayResource generateEcxelFormat() {
    try (
            Workbook workbook = new XSSFWorkbook()) {
      // Crear una hoja nueva
      Sheet sheet = workbook.createSheet("Reporte");
      sheet.autoSizeColumn(0);

      // Crear estilos
      CellStyle headerStyle = workbook.createCellStyle();
      headerStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
      headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      headerStyle.setBorderBottom(BorderStyle.THIN);
      headerStyle.setBorderTop(BorderStyle.THIN);
      headerStyle.setBorderLeft(BorderStyle.THIN);
      headerStyle.setBorderRight(BorderStyle.THIN);
      headerStyle.setAlignment(HorizontalAlignment.CENTER);
      headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);


      Font headerFont = workbook.createFont();
      headerFont.setBold(true);
      headerFont.setColor(IndexedColors.WHITE.getIndex());
      headerStyle.setFont(headerFont);

      // Crear estilo para datos
      CellStyle dataStyle = workbook.createCellStyle();
      dataStyle.setBorderBottom(BorderStyle.THIN);
      dataStyle.setBorderTop(BorderStyle.THIN);
      dataStyle.setBorderLeft(BorderStyle.THIN);
      dataStyle.setBorderRight(BorderStyle.THIN);

      // Crear encabezados
      Row headerRow = sheet.createRow(0);
      String[] columns = {"CEDULA", "NOMBRE", "CARGO", "CENTROCOSTOS", "PROYECTO", "FECHA", "NOVEDAD", "H. ENT.", "H. SAL.", "CONTRATISTA"
      };


      for (int i = 0; i < 37; i++) {

        Cell cell = headerRow.createCell(i);
        CellStyle cellStyleDefault = workbook.createCellStyle();
        cell.setCellStyle(this.setAllBordersAndCenter(cellStyleDefault));
        if (i < columns.length) {
          cell.setCellStyle(headerStyle);
          sheet.setColumnWidth(i, 15 * 256);
          cell.setCellValue(columns[i]);
        }

        if (i == 10) {
          CellStyle cellStyle = workbook.createCellStyle();
          CellStyle cellStyleWithBorders = this.setAllBordersAndCenter(cellStyle);
          cellStyleWithBorders.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
          cellStyleWithBorders.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          Font font = workbook.createFont();
          font.setBold(true);
          font.setColor(IndexedColors.WHITE.getIndex());
          cellStyle.setFont(font);
          cell.setCellStyle(cellStyle);
          cell.setCellValue("TOTAL HORAS");
          continue;
        }
        if (i == 18) {
          CellStyle cellStyle = workbook.createCellStyle();
          CellStyle cellStyleWithBorders = this.setAllBordersAndCenter(cellStyle);
          cellStyleWithBorders.setFillForegroundColor(IndexedColors.GREEN.getIndex());
          cellStyleWithBorders.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          Font font = workbook.createFont();
          font.setBold(true);
          font.setColor(IndexedColors.WHITE.getIndex());
          cellStyle.setFont(font);
          cell.setCellStyle(cellStyle);
          cell.setCellValue("TOTAL COSTO");
          continue;
        }
        if (i == 27) {
          CellStyle cellStyle = workbook.createCellStyle();
          CellStyle cellStyleWithBorders = this.setAllBordersAndCenter(cellStyle);
          cellStyleWithBorders.setFillForegroundColor(IndexedColors.AQUA.getIndex());
          cellStyleWithBorders.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          Font font = workbook.createFont();
          font.setBold(true);
          font.setColor(IndexedColors.WHITE.getIndex());
          cellStyle.setFont(font);
          cell.setCellStyle(cellStyle);
          cell.setCellValue("OTROS COSTOS");
        }
      }

      Row secondRow = sheet.createRow(1);
//      Stack<String> costCode = new Stack<>();
//      costCode.addAll(Arrays.asList("12", "91780", "1231", "138", "6873", "523", "26", "794"));

      Stack<String> subTitle = new Stack<>();
      subTitle.addAll(Arrays.asList("VALOR HORA", "BONO", "PEOPLE PAST", "PRESTACIONES", "SEGURIDAD SOCIAL", "SUBSIDIO DE TRANSPORTE", "SALARIO BASICO"));

      Stack<String> subTitleThirdRow = new Stack<>();
      subTitleThirdRow.addAll(Arrays.asList("COSTO DIARIO\n(HOY)", "JORNAL DIARIO", "COSTO TOTAL", " RECARGO DOMINICAL Y FESTIVO NOCTURNO 110%", "HORA EXTRA NOCTURNA DOMINICAL Y FESTIVA 250%", " HORA EXTRA DIURNA DOMINICAL Y FESTIVA 200%", "DOMINICALES Y FESTIVOS 1.75%", " RECARGO NOCTURNO. 0.35%", "EXTRA NOCTURNA 1.75%", " EXTRA DIURNA 1.25%", "HORA", "JORNAL", "RECARGO DOMINICAL Y FESTIVO NOCTURNO 110%", "HORA EXTRA NOCTURNA DOMINICAL Y FESTIVA 250%", "HORA EXTRA DIURNA DOMINICAL Y FESTIVA 200%", "DOMINICALES Y FESTIVOS 1.75%", "RECARGO NOCTURNO. 0.35%", "EXTRA NOCTURNA 1.75%", "EXTRA DIURNA 1.25%", "HORAS JORNAL"));

      for (int i = 0; i <= 36; i++) {
        Cell cell = secondRow.createCell(i);
        CellStyle cellStyleDefault = workbook.createCellStyle();
        cell.setCellStyle(this.setAllBordersAndCenter(cellStyleDefault));


        if (i >= 10 && i <= 26) {
          cell.setCellValue("FORMULA");
        }

        if (i >= 27 && i <= 32) {
          CellStyle cellStyle = workbook.createCellStyle();
          cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
          cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          cell.setCellStyle(cellStyle);
          cell.setCellValue(subTitle.pop());
        }

        if (i >= 33 && i <= 34) {
          CellStyle cellStyle = workbook.createCellStyle();
          cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
          cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          cell.setCellStyle(cellStyle);
          cell.setCellValue("FORMULA");
        }
        if (i == 35) {
          CellStyle cellStyle = workbook.createCellStyle();
          cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
          cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          cell.setCellStyle(cellStyle);
          cell.setCellValue(subTitle.pop());
        }

        if (i == 36) {
          CellStyle cellStyle = workbook.createCellStyle();
          cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
          cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          cell.setCellStyle(cellStyle);
          cell.setCellValue("FORMULA");
        }
      }

      for (int i = 27; i <= 32; i++) {
        sheet.addMergedRegion(new CellRangeAddress(1, 2, i, i));
      }

      sheet.addMergedRegion(new CellRangeAddress(1, 2, 35,35 ));


      Row thirdRow = sheet.createRow(2);
      thirdRow.setHeight((short) 600);

      for (int i = 0; i <= 36; i++) {
        Cell cell = thirdRow.createCell(i);
        CellStyle cellStyleDefault = workbook.createCellStyle();
        cell.setCellStyle(this.setAllBordersAndCenter(cellStyleDefault));

        if (i >= 10 && i <= 26) {
          if (i <= 17) {
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(cellStyle);
          } else {
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(cellStyle);
          }
          cell.setCellValue(subTitleThirdRow.pop());
        }

        if (i >= 33 && i <= 34) {
          CellStyle cellStyle = workbook.createCellStyle();
          cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
          cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          cell.setCellStyle(cellStyle);
          cell.setCellValue(subTitleThirdRow.pop());
        }

        if (i == 36) {
          CellStyle cellStyle = workbook.createCellStyle();
          cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
          cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          cellStyle.setWrapText(true);
          cell.setCellStyle(cellStyle);
          cell.setCellValue(subTitleThirdRow.pop());
        }
      }


      sheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 17));
      sheet.addMergedRegion(new CellRangeAddress(0, 0, 18, 26));
      sheet.addMergedRegion(new CellRangeAddress(0, 0, 27, 36));

      // Agregar datos de ejemplo
//      Object[][] data = {
//              {1, "Producto A", 100.50, 2, "=C2*D2"},
//              {2, "Producto B", 50.75, 3, "=C3*D3"},
//              {3, "Producto C", 75.25, 1, "=C4*D4"}
//      };
//
//      int rowNum = 1;
//      for (Object[] rowData : data) {
//        Row row = sheet.createRow(rowNum++);
//
//        for (int colNum = 0; colNum < rowData.length; colNum++) {
//          Cell cell = row.createCell(colNum);
//          cell.setCellStyle(dataStyle);
//
//          Object value = rowData[colNum];
//          if (value instanceof String) {
//            // Para las fórmulas
//            if (((String) value).startsWith("=")) {
//              cell.setCellFormula(((String) value).substring(1));
//            } else {
//              cell.setCellValue((String) value);
//            }
//          } else if (value instanceof Integer) {
//            cell.setCellValue((Integer) value);
//          } else if (value instanceof Double) {
//            cell.setCellValue((Double) value);
//          }
//        }
//      }

      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      workbook.write(bos);

      byte[] bytes = bos.toByteArray();
      return new ByteArrayResource(bytes);

    } catch (IOException e) {
      throw new RuntimeException("Error al generar el archivo Excel", e);
    }
  }

  private CellStyle setAllBordersAndCenter(CellStyle cellStyle) {
    cellStyle.setBorderBottom(BorderStyle.THIN);
    cellStyle.setBorderTop(BorderStyle.THIN);
    cellStyle.setBorderLeft(BorderStyle.THIN);
    cellStyle.setBorderRight(BorderStyle.THIN);
    cellStyle.setAlignment(HorizontalAlignment.CENTER);
    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    return cellStyle;
  }

}
