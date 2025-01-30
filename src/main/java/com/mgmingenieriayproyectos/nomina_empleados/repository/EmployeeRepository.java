package com.mgmingenieriayproyectos.nomina_empleados.repository;

import com.mgmingenieriayproyectos.nomina_empleados.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository <Employee,Long> {
    Optional<Employee> findByIdentification(String name);
    boolean existsByIdentification(String identification);
}
