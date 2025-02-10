package com.mgmingenieriayproyectos.nomina_empleados.controller;

import com.mgmingenieriayproyectos.nomina_empleados.model.Project;
import com.mgmingenieriayproyectos.nomina_empleados.repository.ProjectRepository;
import com.mgmingenieriayproyectos.nomina_empleados.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:5173", "https://8bnmn78c-5173.use2.devtunnels.ms"})
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

  ProjectService projectService;
//  Todo refactor this
  ProjectRepository projectRepository;

  public ProjectController(ProjectService projectService,ProjectRepository projectRepository) {
    this.projectService = projectService;
    this.projectRepository = projectRepository;
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
//  Todo refactor this

  @GetMapping
  public List<Project> getEmployees() {
    return projectRepository.findAll();
  }

  @GetMapping("{id}")
  public ResponseEntity<Project> getProyect(@PathVariable Long id) {
    Optional<Project> project = projectService.findById(id);
    return project.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }
}
