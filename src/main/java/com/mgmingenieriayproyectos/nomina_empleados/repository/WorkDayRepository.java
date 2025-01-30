package com.mgmingenieriayproyectos.nomina_empleados.repository;

import com.mgmingenieriayproyectos.nomina_empleados.model.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkDayRepository extends JpaRepository <WorkDay, Long> {
}
