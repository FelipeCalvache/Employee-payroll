package com.mgmingenieriayproyectos.nomina_empleados.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class WorkDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BiometricRecord start;
    private BiometricRecord end;
}
