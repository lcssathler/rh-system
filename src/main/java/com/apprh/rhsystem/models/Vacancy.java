package com.apprh.rhsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "vacancies")
public class Vacancy implements Serializable {
    private static final long SERIAL_VERSION_UID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String data;
    @NotEmpty
    private String salary;
    @ManyToMany
    @JoinTable( name = "vacancy_applicant",
                joinColumns = @JoinColumn(name = "vacancy_id"),
                inverseJoinColumns = @JoinColumn(name = "applicant_id"))
    private Set<Applicant> applicants;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Set<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(Set<Applicant> applicants) {
        this.applicants = applicants;
    }
}
