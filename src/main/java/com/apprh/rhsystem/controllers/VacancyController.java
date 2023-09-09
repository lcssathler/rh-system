package com.apprh.rhsystem.controllers;

import com.apprh.rhsystem.models.Applicant;
import com.apprh.rhsystem.models.Vacancy;
import com.apprh.rhsystem.repository.ApplicantRepository;
import com.apprh.rhsystem.repository.VacancyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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

    @RequestMapping(value = "/vacancies", method = RequestMethod.GET)
    public ModelAndView allVacancies() {
        ModelAndView modeView = new ModelAndView("vacancy/allVacancies");
        Iterable<Vacancy> vacancies = vacancyRepository.findAll();
        modeView.addObject("vacancies", vacancies);
        return modeView;
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ModelAndView vacancyDetails(@PathVariable(value = "code") Long code) {
        Vacancy vacancy = vacancyRepository.findByCode(code);
        ModelAndView modelView = new ModelAndView("vacancy/vacancyDetails");
        modelView.addObject("vacancy", vacancy);

        //Returns all candidates who applied to a vacancy
        Iterable<Applicant> applicants = applicantRepository.findByVacancy(vacancy);
        modelView.addObject("applicants", applicants);
        return modelView;
    }

    @RequestMapping(value = "/deleteVacancy")
    public String deleteVacancy(Long code) throws Exception {
        Vacancy vacancy = vacancyRepository.findByCode(code);
        if (vacancy == null) {
            throw new Exception("Vacancy not found");
        }

        vacancyRepository.delete(vacancy);
        return "redirect:vacancies";
    }

    public String vacancyDetailsPost(@PathVariable("code") Long code, @Valid Applicant applicant,
                                     BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Invalid fields");
        }

        if (applicantRepository.findByDocument(applicant.getDocument()) != null) {
            attributes.addFlashAttribute("message_error", "Document already registered");
        }

        Vacancy vacancy = vacancyRepository.findByCode(code);
        applicant.addVacancy(vacancy);
        applicantRepository.save(applicant);
        attributes.addFlashAttribute("message", "Applicant saved successfully");
        return "redirect:/{code}";
    }
}
