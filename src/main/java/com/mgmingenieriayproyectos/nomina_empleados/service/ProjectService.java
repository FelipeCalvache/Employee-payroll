package com.mgmingenieriayproyectos.nomina_empleados.service;

import com.mgmingenieriayproyectos.nomina_empleados.model.Project;
import com.mgmingenieriayproyectos.nomina_empleados.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public Optional<Project> findById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            log.warn("No se pudo encontrar el proyecto",
                    id,project);
        }
        return project;
    }

    public Optional<Project> findByName(String name) {
        Optional<Project> project = projectRepository.findByName(name);
        if (project.isEmpty()) {
            log.warn("No se pudo procesar el registro: Empleado={} o Proyecto={} no encontrado",
                    name, project);
        }
        return project;
    }
}
