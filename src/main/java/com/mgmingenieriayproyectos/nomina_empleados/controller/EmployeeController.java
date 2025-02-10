package com.mgmingenieriayproyectos.nomina_empleados.controller;

import com.mgmingenieriayproyectos.nomina_empleados.model.Employee;
import com.mgmingenieriayproyectos.nomina_empleados.model.WorkDay;
import com.mgmingenieriayproyectos.nomina_empleados.repository.EmployeeRepository;
import com.mgmingenieriayproyectos.nomina_empleados.repository.WorkDayRepository;
import com.mgmingenieriayproyectos.nomina_empleados.service.EmployeeService;
import com.mgmingenieriayproyectos.nomina_empleados.service.WorkDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "https://8bnmn78c-5173.use2.devtunnels.ms"})
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
  private final EmployeeService employeeService;

  private final WorkDayService workDayService;
  private final WorkDayRepository workDayRepository;

  private final EmployeeRepository employeeRepository;

  public EmployeeController(EmployeeService employeeService, WorkDayService workDayService, EmployeeRepository employeeRepository,WorkDayRepository workDayRepository) {
    this.employeeService = employeeService;
    this.workDayService = workDayService;
    this.employeeRepository = employeeRepository;
    this.workDayRepository = workDayRepository;
  }

  @PostMapping("/upload")
  public ResponseEntity<String> saveEmployee(@RequestParam("file") MultipartFile file) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body("Numero de empleados creados: " + employeeService.loadEmployeeBioFile(file));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el empleado");
    }
  }

  //  Todo refactorizar este metodo
  @GetMapping
  public List<Employee> getEmployees() {
    return employeeRepository.findAll();
  }

  //  Todo refactorizar este metodo
  @GetMapping("/workdays")
  public List<WorkDay> getWorkDays() {
    return workDayRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<List<WorkDay>> getWorkDaysByEmployee(@PathVariable Long id) {
    try {
      List<WorkDay> workDays = workDayService.obtenerDiasDeTrabajoPorEmpleado(id);
      return ResponseEntity.status(HttpStatus.OK).body(workDays);
    } catch (Error e) {
      log.error("Error al consultar la base de datos ", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }
}