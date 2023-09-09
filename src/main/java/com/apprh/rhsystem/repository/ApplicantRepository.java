package com.apprh.rhsystem.repository;

import com.apprh.rhsystem.models.Applicant;
import com.apprh.rhsystem.models.Vacancy;
import org.springframework.data.repository.CrudRepository;

public interface ApplicantRepository extends CrudRepository<Applicant, Long> {
    Iterable<Applicant> findByVacancy(Vacancy vacancy);

    Applicant findByDocument(String document);
}
