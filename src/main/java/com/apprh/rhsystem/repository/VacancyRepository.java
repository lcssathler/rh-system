package com.apprh.rhsystem.repository;

import com.apprh.rhsystem.models.Vacancy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VacancyRepository extends CrudRepository<Vacancy, Long> {
    Vacancy findByCode(Long code);
    List<Vacancy> findByName(String name);

}
