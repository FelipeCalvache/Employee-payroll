package com.mgmingenieriayproyectos.nomina_empleados.model;

import jakarta.persistence.*;

@Entity
@Table(name = "work_days")
public class WorkDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "start_id")
    private BiometricRecord start;

    @OneToOne
    @JoinColumn(name = "end_id", nullable = true)
    private BiometricRecord end;

    @ManyToOne
    @JoinColumn(name = "employee_id")
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
