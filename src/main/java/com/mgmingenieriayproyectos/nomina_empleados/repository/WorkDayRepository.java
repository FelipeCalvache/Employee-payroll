package com.mgmingenieriayproyectos.nomina_empleados.repository;

import com.mgmingenieriayproyectos.nomina_empleados.model.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkDayRepository extends JpaRepository <WorkDay, Long> {
  List<WorkDay> findByEmployeeId(Long employeeId);
}
