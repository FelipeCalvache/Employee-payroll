package com.mgmingenieriayproyectos.nomina_empleados.service;

import com.mgmingenieriayproyectos.nomina_empleados.model.Project;
import com.mgmingenieriayproyectos.nomina_empleados.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public  ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public void saveProject(Project project){
        projectRepository.save(project);
    }

    public Optional<Project> findById(Long id){
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            System.out.println("Project found: " + project.get());
        } else {
            System.out.println("No project found with id: " + id);
        }
        return project;
    }

    public Optional<Project> findByName(String name){
        Optional<Project> project = projectRepository.findByName(name);
        if (project.isPresent()) {
            System.out.println("Project found: " + project.get().getName());
        } else {
            System.out.println("No project found with name: " + name );
        }
        return project;
    }
}
