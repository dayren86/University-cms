package com.university.controller;

import com.university.model.Lesson;
import com.university.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.time.LocalTime;

@RequiredArgsConstructor
@RequestMapping("/lesson")
@Controller
@RolesAllowed({"ADMIN", "TEACHER"})
public class LessonController {

    private final LessonService lessonService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final TimetableService timetableService;
    private final UniversityService universityService;

    @GetMapping
    @RolesAllowed({"TEACHER", "ADMIN", "STUDENT"})
    public ModelAndView lesson() {
        ModelAndView lessonModelAndView = new ModelAndView("lesson");
        lessonModelAndView.addObject("lessonList", lessonService.findAllLesson());

        return lessonModelAndView;
    }

    @GetMapping("/template")
    public ModelAndView lessonCreateMVC() {
        ModelAndView lessonModelAndView = new ModelAndView("lessoncreate");
        lessonModelAndView.addObject("teacherList", teacherService.findAllTeacher());
        lessonModelAndView.addObject("groupList", groupService.findAllGroup());
        lessonModelAndView.addObject("timetableList", timetableService.findAllTimetable());
        lessonModelAndView.addObject("universityList", universityService.findAllTimetable());

        return lessonModelAndView;
    }

    @PostMapping()
    public String createLesson(@ModelAttribute(name = "lesson") Lesson lesson, @RequestParam(name = "timeString") String time) {
        LocalTime parseTime = LocalTime.parse(time);

        lesson.setTime(parseTime);

        lessonService.createLesson(lesson);

        return "redirect:/lesson";
    }

    @PostMapping("/{id}")
    public String deleteLesson(@PathVariable("id") long id) {
        lessonService.deleteLesson(lessonService.findLessonById(id));

        return "redirect:/lesson";
    }

    @GetMapping("/changetemplate/{id}")
    public ModelAndView updateLessonMVC(@PathVariable("id") long id) {
        Lesson lessonById = lessonService.findLessonById(id);

        ModelAndView lessonUpdateMVC = new ModelAndView("lessonupdate");
        lessonUpdateMVC.addObject("lessonId", lessonById.getId());
        lessonUpdateMVC.addObject("lessonName", lessonById.getNameLesson());
        lessonUpdateMVC.addObject("lessonTime", lessonById.getTime());
        lessonUpdateMVC.addObject("lessonAudience", lessonById.getAudience());
        lessonUpdateMVC.addObject("teacherList", teacherService.findAllTeacher());
        lessonUpdateMVC.addObject("groupList", groupService.findAllGroup());
        lessonUpdateMVC.addObject("timetableList", timetableService.findAllTimetable());
        lessonUpdateMVC.addObject("universityList", universityService.findAllTimetable());

        return lessonUpdateMVC;
    }

    @PostMapping("/update")
    public String updateLesson(@ModelAttribute("lesson") Lesson lesson) {
        lessonService.updateLesson(lesson);

        return "redirect:/lesson";
    }
}
