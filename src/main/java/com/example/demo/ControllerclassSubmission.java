package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class ControllerclassSubmission {

    @Autowired
    private TableclassRepo repo;

    @RequestMapping("/")
    public String showForm(Model model) {
        model.addAttribute("tableclass", new Tableclass());
        return "NewFile"; // JSP for form input
    }

    @PostMapping("/details")
    public String postDetails(@Valid @ModelAttribute("tableclass") Tableclass tableclass, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "NewFile"; // Return to form view if validation errors exist
        }

        // Save the Tableclass object if no validation errors
        repo.save(tableclass);

        // Add details to modelMap for displaying
        modelMap.put("cid", tableclass.getCid());
        modelMap.put("cname", tableclass.getCname());
        modelMap.put("cemail", tableclass.getCemail());
        modelMap.put("dateOfBirth", tableclass.getDateOfBirth());
        modelMap.put("country", tableclass.getCountry());
        modelMap.put("state", tableclass.getState());
        modelMap.put("timezone", tableclass.getTimezone());
        modelMap.put("time", tableclass.getTime());
        modelMap.put("ampm", tableclass.getAmpm());

        return "NewFile1"; // Redirect to result view (NewFile1.jsp)
    }
    }


