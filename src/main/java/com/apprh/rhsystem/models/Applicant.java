package com.apprh.rhsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "applicants")
public class Applicant implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String document;
    @NotEmpty
    private String name;
    @NotEmpty @Column(unique = true)
    private String email;
    @ManyToMany(mappedBy = "applicants")
    private Set<Vacancy> vacanciesApplied;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Vacancy> getVacanciesApplied() {
        return vacanciesApplied;
    }

    public void setVacanciesApplied(Set<Vacancy> vacanciesApplied) {
        this.vacanciesApplied = vacanciesApplied;
    }
}
