package com.university.controller;

import com.university.model.University;
import com.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/university")
@Controller
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping
    public ModelAndView university() {
        ModelAndView universityModelAndView = new ModelAndView("university");
        universityModelAndView.addObject("universityList", universityService.findAllTimetable());

        return universityModelAndView;
    }

    @GetMapping("/template")
    public ModelAndView UniversityCreateMVC() {
        return new ModelAndView("universitycreate");
    }

    @PostMapping()
    public String universityCreate(@ModelAttribute("university") University university) {
        universityService.createUniversity(university);

        return "redirect:/university";
    }
}
