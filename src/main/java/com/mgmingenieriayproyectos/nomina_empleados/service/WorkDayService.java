
package com.mgmingenieriayproyectos.nomina_empleados.service;

import com.mgmingenieriayproyectos.nomina_empleados.model.BiometricRecord;
import com.mgmingenieriayproyectos.nomina_empleados.model.Employee;
import com.mgmingenieriayproyectos.nomina_empleados.model.WorkDay;
import com.mgmingenieriayproyectos.nomina_empleados.repository.WorkDayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Service
public class WorkDayService {

  private static final Logger log = LoggerFactory.getLogger(WorkDayService.class);
  private final WorkDayRepository workDayRepository;


  public WorkDayService(WorkDayRepository workDayRepository) {
    this.workDayRepository = workDayRepository;
  }

  public void processWorkdays(Map<String, List<BiometricRecord>> groupByIdentification) {

    for (Map.Entry<String, List<BiometricRecord>> entry : groupByIdentification.entrySet()) {
      if (entry.getValue().size() == 1) {
        BiometricRecord biometricStart = entry.getValue().getFirst();

        LocalDateTime start = entry.getValue().getFirst().getDate().atTime(entry.getValue().getFirst().getTime());
        Employee employee = entry.getValue().getFirst().getEmployee();
        long duration = Duration.between(start, ZonedDateTime.now(ZoneId.of("America/Bogota"))).toHours();

        saveWorkDay(new WorkDay(biometricStart, null, employee, duration));
      }

      if (entry.getValue().size() == 2) {
        BiometricRecord biometricStart = entry.getValue().getFirst();
        BiometricRecord biometricEnd = entry.getValue().get(1);

        LocalDateTime start = entry.getValue().getFirst().getDate().atTime(entry.getValue().getFirst().getTime());
        LocalDateTime end = entry.getValue().get(1).getDate().atTime(entry.getValue().get(1).getTime());
        Employee employee = entry.getValue().getFirst().getEmployee();

        if (start.isBefore(end)) {
          long duration = Duration.between(start, end).toHours();
          saveWorkDay(new WorkDay(biometricStart, biometricEnd, employee, duration));
        } else {
          long duration = Duration.between(end,start).toHours();
          saveWorkDay(new WorkDay(biometricEnd, biometricStart, employee, duration));
        }
      }

      if (entry.getValue().size() > 2) {
        log.warn("Probablemente hay un error con los datos del empleado{} identificacion{}", entry.getValue().getFirst().getEmployee().getIdentification(), entry.getValue().getFirst().getName());
      }
    }
  }

  public void saveWorkDay(WorkDay workDay) {
    workDayRepository.save(workDay);
  }

  public List<WorkDay> obtenerDiasDeTrabajoPorEmpleado(Long employeeId) {
    return workDayRepository.findByEmployeeId(employeeId);
  }
}
