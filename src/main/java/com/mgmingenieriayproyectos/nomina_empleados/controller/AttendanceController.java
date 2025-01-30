package com.mgmingenieriayproyectos.nomina_empleados.controller;

import com.mgmingenieriayproyectos.nomina_empleados.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            attendanceService.processAttendanceFile(file);
            return ResponseEntity.ok("Archivo procesado exitosamente");
        } catch (
                Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el archivo");
        }
    }
}

