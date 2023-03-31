package com.university.controller;

import com.university.model.Student;
import com.university.service.GroupService;
import com.university.service.StudentService;
import com.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RequestMapping("/student")
@Controller
@RolesAllowed({"ADMIN"})
public class StudentController {

    private final StudentService studentService;
    private final UniversityService universityService;
    private final GroupService groupService;

    @RolesAllowed({"ADMIN", "STUDENT", "TEACHER"})
    @GetMapping
    public ModelAndView student() {
        ModelAndView studentModelAndView = new ModelAndView("student");
        studentModelAndView.addObject("studentList", studentService.findAllStudent());

        return studentModelAndView;
    }

    @GetMapping("/template")
    public ModelAndView createStudentMVC() {
        ModelAndView studentModelAndView = new ModelAndView("studentcreate");
        studentModelAndView.addObject("universityList", universityService.findAllTimetable());
        studentModelAndView.addObject("groupList", groupService.findAllGroup());

        return studentModelAndView;
    }

    @PostMapping()
    public String createStudent(@ModelAttribute(name = "student") Student student) {
        studentService.createStudent(student);

        return "redirect:/student";
    }

    @PostMapping("/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(studentService.findStudentById(id));

        return "redirect:/student";
    }

    @GetMapping("/changetemplate/{id}")
    public ModelAndView updateStudentMVC(@PathVariable("id") long id) {
        Student studentById = studentService.findStudentById(id);

        ModelAndView studentUpdateModelAndView = new ModelAndView("studentupdate");
        studentUpdateModelAndView.addObject("studentId", studentById.getId());
        studentUpdateModelAndView.addObject("studentFirstName", studentById.getFirstName());
        studentUpdateModelAndView.addObject("studentLastName", studentById.getLastName());
        studentUpdateModelAndView.addObject("groupName", studentById.getGroup());
        studentUpdateModelAndView.addObject("groupList", groupService.findAllGroup());
        studentUpdateModelAndView.addObject("universityList", universityService.findAllTimetable());
        studentUpdateModelAndView.addObject("universityName", studentById.getUniversity());

        return studentUpdateModelAndView;
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute(name = "student") Student student) {
        studentService.updateStudent(student);

        return "redirect:/student";
    }
}
