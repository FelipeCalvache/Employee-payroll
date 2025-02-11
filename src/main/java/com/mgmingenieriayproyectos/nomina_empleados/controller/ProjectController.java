package com.mgmingenieriayproyectos.nomina_empleados.controller;

import com.mgmingenieriayproyectos.nomina_empleados.model.Project;
import com.mgmingenieriayproyectos.nomina_empleados.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @PostMapping
    public ResponseEntity<String> saveProject(@RequestBody Project project) {
        try {
            projectService.saveProject(project);
            return ResponseEntity.status(HttpStatus.OK).body("Proyecto creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el proyecto");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Project> getProyect(@PathVariable Long id) {
        Optional<Project> project = projectService.findById(id);
        return project.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
