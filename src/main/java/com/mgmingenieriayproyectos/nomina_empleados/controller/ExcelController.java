package com.mgmingenieriayproyectos.nomina_empleados.controller;

import com.mgmingenieriayproyectos.nomina_empleados.service.ExcelService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

// Backend (Java con Spring Boot)
@RestController
@CrossOrigin(origins = "http://localhost:5173") // Ajusta según tu configuración
public class ExcelController {

  private final ExcelService excelService;

  public ExcelController(ExcelService excelService) {
    this.excelService = excelService;
  }

  @GetMapping("/api/download/excel")
  public ResponseEntity<ByteArrayResource> downloadExcel() {
    ByteArrayResource format = excelService.generateEcxelFormat();
    byte[] bytes = format.getByteArray();

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.xlsx")
            .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .contentLength(bytes.length)
            .body(format);
  }
}


