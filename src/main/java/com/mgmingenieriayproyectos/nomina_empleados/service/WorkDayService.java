
package com.mgmingenieriayproyectos.nomina_empleados.service;

import com.mgmingenieriayproyectos.nomina_empleados.model.BiometricRecord;
import com.mgmingenieriayproyectos.nomina_empleados.model.WorkDay;
import com.mgmingenieriayproyectos.nomina_empleados.repository.WorkDayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
        saveWorkDay(new WorkDay(entry.getValue().getFirst(), null, entry.getValue().getFirst().getEmployee()));
      }
      if (entry.getValue().size() == 2) {
        LocalDateTime localDateTime1 = entry.getValue().getFirst().getDate().atTime(entry.getValue().getFirst().getTime());
        LocalDateTime localDateTime2 = entry.getValue().get(1).getDate().atTime(entry.getValue().get(1).getTime());
        if (localDateTime1.isAfter(localDateTime2)) {
          saveWorkDay(new WorkDay(entry.getValue().getFirst(), entry.getValue().get(1), entry.getValue().getFirst().getEmployee()));
        } else {
          saveWorkDay(new WorkDay(entry.getValue().get(1), entry.getValue().getFirst(), entry.getValue().getFirst().getEmployee()));
        }
      }

      if (entry.getValue().size() > 2) {
        log.warn("Probablemente hay un error con los datos del empleado{} identificacion{}", entry.getValue().getFirst().getEmployee().getIdentification() , entry.getValue().getFirst().getName());
      }
    }
  }

  public void saveWorkDay(WorkDay workDay) {
    workDayRepository.save(workDay);
  }
}
