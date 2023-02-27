package com.university.controller;

import com.university.model.Timetable;
import com.university.service.TimetableService;
import com.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping("/timetable")
@Controller
public class TimetableController {

    private final TimetableService timetableService;
    private final UniversityService universityService;

    @GetMapping
    public ModelAndView timetable() {
        ModelAndView timetableModelAndView = new ModelAndView("timetable");
        timetableModelAndView.addObject("timetableList", timetableService.findAllTimetable());

        return timetableModelAndView;
    }

    @GetMapping("/createmvc")
    public ModelAndView timetableCreateMVC() {
        ModelAndView timetableCreate = new ModelAndView("timetablecreate");
        timetableCreate.addObject("universityList", universityService.findAllTimetable());

        return timetableCreate;
    }

    @PostMapping("/create")
    public String timetableCreate(@ModelAttribute("timetable") Timetable timetable, @RequestParam(name = "dateString") String date) {
        LocalDate parseDate = LocalDate.parse(date);
        timetable.setLocalDate(parseDate);

        timetableService.createTimetable(timetable);

        return "redirect:/timetable";
    }

    @GetMapping("/update/{id}")
    public ModelAndView timetableUpdateMVC(@PathVariable("id") long id) {
        Timetable timetableById = timetableService.findTimetableById(id);

        ModelAndView timetableUpdateMVC = new ModelAndView("timetableupdate");
        timetableUpdateMVC.addObject("timetableId", timetableById.getId());
        timetableUpdateMVC.addObject("timetableDate", timetableById.getLocalDate());
        timetableUpdateMVC.addObject("timetableLessonSet", timetableById.getLessonSet());
        timetableUpdateMVC.addObject("universityList", universityService.findAllTimetable());

        return timetableUpdateMVC;
    }

    @PostMapping("/delete/{id}")
    public String timetableDelete(@PathVariable("id") long id) {
        timetableService.deleteTimetable(timetableService.findTimetableById(id));

        return "redirect:/timetable";
    }
}
