package com.mgmingenieriayproyectos.nomina_empleados.repository;

import com.mgmingenieriayproyectos.nomina_empleados.model.BiometricRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRecordRepository extends JpaRepository<BiometricRecord,Long> {

}
