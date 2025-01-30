package com.mgmingenieriayproyectos.nomina_empleados.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String identification;
    private String password;
    private String role;

    public Employee() {
    }

    public Employee( String name, String identification, String password, String role) {
        this.name = name;
        this.identification = identification;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}




