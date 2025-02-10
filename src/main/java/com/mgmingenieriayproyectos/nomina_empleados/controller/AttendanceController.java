package com.mgmingenieriayproyectos.nomina_empleados.controller;

import com.mgmingenieriayproyectos.nomina_empleados.service.AttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = {"http://localhost:5173", "https://8bnmn78c-5173.use2.devtunnels.ms"})
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

  private static final Logger log = LoggerFactory.getLogger(AttendanceController.class);
  AttendanceService attendanceService;

  public AttendanceController(AttendanceService attendanceService) {
    this.attendanceService = attendanceService;
  }

  @PostMapping("/upload")
  public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
    log.warn("Entra la peticion a la api");
    try {
      attendanceService.processAttendanceFile(file);
      return ResponseEntity.ok("Archivo procesado exitosamente");
    } catch (
            Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el archivo");
    }
  }
}

