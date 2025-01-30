package com.mgmingenieriayproyectos.nomina_empleados.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "biometric_records")
public class BiometricRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Employee employee;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String recordOrigin;
    @ManyToOne
    private Project project;

    public BiometricRecord() {
    }

    public BiometricRecord(Employee employee, String name, LocalDate date, LocalTime time, String recordOrigin, Project project) {
        this.employee = employee;
        this.name = name;
        this.date = date;
        this.time = time;
        this.recordOrigin = recordOrigin;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getRecordOrigin() {
        return recordOrigin;
    }

    public void setRecordOrigin(String recordOrigin) {
        this.recordOrigin = recordOrigin;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BiometricRecord that = (BiometricRecord) object;
        return Objects.equals(employee, that.employee) && Objects.equals(name, that.name) && Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(recordOrigin, that.recordOrigin) && Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, name, date, time, recordOrigin, project);
    }
}
