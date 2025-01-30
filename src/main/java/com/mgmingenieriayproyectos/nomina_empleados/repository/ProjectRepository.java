package com.mgmingenieriayproyectos.nomina_empleados.repository;

import com.mgmingenieriayproyectos.nomina_empleados.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);
}
