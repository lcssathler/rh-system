package com.apprh.rhsystem.repository;

import com.apprh.rhsystem.models.Vacancy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends CrudRepository<Vacancy, Long> {
    Vacancy findByCode(Long code);
    List<Vacancy> findByName(String name);
}
