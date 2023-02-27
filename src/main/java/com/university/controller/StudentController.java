package com.university.controller;

import com.university.model.Student;
import com.university.service.GroupService;
import com.university.service.StudentService;
import com.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/student")
@Controller
public class StudentController {

    private final StudentService studentService;
    private final UniversityService universityService;
    private final GroupService groupService;

    @GetMapping
    public ModelAndView student() {
        ModelAndView studentModelAndView = new ModelAndView("student");
        studentModelAndView.addObject("studentList", studentService.findAllStudent());

        return studentModelAndView;
    }

    @GetMapping("/createmvc")
    public ModelAndView createStudentMVC() {
        ModelAndView studentModelAndView = new ModelAndView("studentcreate");
        studentModelAndView.addObject("universityList", universityService.findAllTimetable());
        studentModelAndView.addObject("groupList", groupService.findAllGroup());

        return studentModelAndView;
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute(name = "student") Student student) {
        studentService.createStudent(student);

        return "redirect:/student";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(studentService.findStudentById(id));

        return "redirect:/student";
    }

    @GetMapping("/update/{id}")
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
