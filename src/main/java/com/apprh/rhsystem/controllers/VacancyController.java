package com.apprh.rhsystem.controllers;

import com.apprh.rhsystem.models.Vacancy;
import com.apprh.rhsystem.repository.ApplicantRepository;
import com.apprh.rhsystem.repository.VacancyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VacancyController {
    @Autowired
    private VacancyRepository vacancyRepository;
    @Autowired
    private ApplicantRepository applicantRepository;

    @RequestMapping(value = "/registerVacancy", method = RequestMethod.GET)
    public String form() {
        return "vacancy/formVacancy";
    }

    @RequestMapping(value = "/registerVacancy", method = RequestMethod.POST)
    public String form(@Valid Vacancy vacancy, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Invalid fields");
            return "redirect:/registerVacancy";
        }

        vacancyRepository.save(vacancy);
        attributes.addFlashAttribute("message", "Vacancy registered successfully");
        return "redirect:/registerVacancy";
    }


}
