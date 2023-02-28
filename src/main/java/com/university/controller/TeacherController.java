package com.university.controller;

import com.university.model.Teacher;
import com.university.repository.TeacherRepository;
import com.university.repository.TimetableRepository;
import com.university.service.TeacherService;
import com.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/teacher")
@Controller
public class TeacherController {

    private final TeacherService teacherService;
    private final UniversityService universityService;
private final TeacherRepository teacherRepository;

    @GetMapping
    public ModelAndView teacher() {
        ModelAndView teacherModelAndView = new ModelAndView("teacher");


        teacherModelAndView.addObject("teacherList", teacherService.findAllTeacher());
//        try {
//            teacherModelAndView.addObject("teacherTimetable",timetableRepository.findTimetableFotTeacher(1L));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return teacherModelAndView;
    }

    @GetMapping("/createmvc")
    public ModelAndView createTeacherMvc() {
        ModelAndView teacherCreateMVC = new ModelAndView("teachercreate");
        teacherCreateMVC.addObject("universityList", universityService.findAllTimetable());

        return teacherCreateMVC;
    }

    @PostMapping("/create")
    public String createTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.createTeacher(teacher);

        return "redirect:/teacher";
    }

    @PostMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        teacherService.deleteTeacher(teacherService.findTeacherById(id));

        return "redirect:/teacher";
    }

    @GetMapping("update/{id}")
    public ModelAndView updateTeacherMVC(@PathVariable("id") long id) {
        Teacher teacherById = teacherService.findTeacherById(id);

        ModelAndView teacherUpdateMVC = new ModelAndView("teacherupdate");
        teacherUpdateMVC.addObject("teacherId", teacherById.getId());
        teacherUpdateMVC.addObject("teacherName", teacherById.getNameTeacher());
        teacherUpdateMVC.addObject("universityList", universityService.findAllTimetable());

        return teacherUpdateMVC;
    }

    @PostMapping("/update")
    public String updateTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.updateTeacher(teacher);

        return "redirect:/teacher";
    }
}
