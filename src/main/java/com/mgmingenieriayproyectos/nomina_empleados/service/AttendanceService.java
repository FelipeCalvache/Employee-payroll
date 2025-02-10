package com.mgmingenieriayproyectos.nomina_empleados.service;


import com.mgmingenieriayproyectos.nomina_empleados.model.BiometricRecord;
import com.mgmingenieriayproyectos.nomina_empleados.model.Employee;
import com.mgmingenieriayproyectos.nomina_empleados.model.Project;
import com.mgmingenieriayproyectos.nomina_empleados.repository.AttendanceRecordRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

  private static final Logger log = LoggerFactory.getLogger(AttendanceService.class);
  private final ProjectService projectService;

  private final EmployeeService employeeService;
  private final WorkDayService workDayService;
  private final AttendanceRecordRepository attendanceRecordRepository;

  public AttendanceService(ProjectService projectService, EmployeeService employeeService, WorkDayService workDayService, AttendanceRecordRepository attendanceRecordRepository) {
    this.projectService = projectService;
    this.employeeService = employeeService;
    this.workDayService = workDayService;
    this.attendanceRecordRepository = attendanceRecordRepository;
  }

  public void processAttendanceFile(MultipartFile file) throws IOException {

    List<BiometricRecord> biometricRecordList = new ArrayList<>();

    try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

      Sheet sheet = workbook.getSheetAt(0);
      for (Row row : sheet) {
        if (row.getRowNum() == 0 || row.getRowNum() == 1) continue;
        log.warn("entro al try");
        String identification = row.getCell(0).getStringCellValue();
        Optional<Employee> employee = employeeService.findByIdentification(identification);

        String name = row.getCell(1).getStringCellValue();
        String stringDate = row.getCell(2).getStringCellValue();
        LocalDate date = LocalDate.parse(stringDate);

        String stringTime = row.getCell(3).getStringCellValue();
        LocalTime time = LocalTime.parse(stringTime);

        String recordOrigin = row.getCell(4).getStringCellValue();

        Optional<Project> project = projectService.findByName(recordOrigin);
        if (project.isPresent() && employee.isPresent()) {
          BiometricRecord biometricRecord = new BiometricRecord(employee.get(), name, date, time, recordOrigin, project.get());
          biometricRecordList.add(biometricRecord);
        } else {
          //Todo manejar el error en el caso en que no exista empleado o proyecto
          log.warn("No se pudo procesar el registro: Empleado={} o Proyecto={} no encontrado",
                  identification, recordOrigin);
        }
      }
    } catch (IOException e) {
      log.error("Error al procesar el archivo Excel", e);
      throw new IOException("Error al procesar el archivo Excel", e);
    }

    Set<BiometricRecord> biometricRecordSet = new HashSet<>(biometricRecordList);

    Map<String, List<BiometricRecord>> groupByIdentification = biometricRecordSet.stream()
            .collect(Collectors.groupingBy(item -> item.getEmployee().getIdentification()));

    for (BiometricRecord biometricRecord : biometricRecordSet) {
      attendanceRecordRepository.save(biometricRecord);
    }

    workDayService.processWorkdays(groupByIdentification);

  }

}
