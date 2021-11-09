package br.com.vibbra.notify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.vibbra.notify.persistence.model.Application;
import br.com.vibbra.notify.service.ApplicationService;

@Controller
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("listApplication", applicationService.getAllApplication());
        return "index";
    }

    @GetMapping("/showNewApplicationForm")
    public String showNewApplicationForm(Model model) {
        Application application = new Application();
        model.addAttribute("application", application);
        return "newApplication";
    }

    @PostMapping("/saveApplication")
    public String saveApplication(@ModelAttribute("application") Application application) {
        // save employee to database
        applicationService.saveApplication(application);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Application application = applicationService.getApplicationById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("application", application);
        return "updateApplication";
    }

    @GetMapping("/deleteApplication/{id}")
    public String deleteApplication(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.applicationService.deleteApplicationById(id);
        return "redirect:/";
    }
}