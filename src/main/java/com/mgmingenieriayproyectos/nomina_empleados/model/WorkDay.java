package com.mgmingenieriayproyectos.nomina_empleados.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class WorkDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BiometricRecord start;
    private BiometricRecord end;
    @ManyToOne
    private Employee employee;

    public Long getId() {
        return id;
    }

    public BiometricRecord getStart() {
        return start;
    }

    public void setStart(BiometricRecord start) {
        this.start = start;
    }

    public BiometricRecord getEnd() {
        return end;
    }

    public void setEnd(BiometricRecord end) {
        this.end = end;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public WorkDay(BiometricRecord start, BiometricRecord end, Employee employee) {
        this.start = start;
        this.end = end;
        this.employee = employee;
    }

    public WorkDay() {
    }
}
