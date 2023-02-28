package com.university.service;

import com.university.model.*;
import com.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityRepository universityRepository;
//    private final StudentService studentService;
//    private final TimetableService timetableService;
//    private final GroupService groupService;
//    private final TeacherService teacherService;
//    private final LessonService lessonService;

    public void createUniversity(University university) {
        universityRepository.save(university);
    }

    public List<University> findAllTimetable() {
        return universityRepository.findAll();
    }

    public University findUniversityById(Long idUniversity) {
        return universityRepository.findById(idUniversity).orElseThrow();
    }
//
//    public void updateUniversity(University university) {
//        University universityById = findUniversityById(university.getId());
//        universityById.setNameUniversity(university.getNameUniversity());
//
//        universityRepository.save(universityById);
//    }
//
//    public void deleteUniversity(University university) {
//        universityRepository.delete(university);
//    }
//
//    public void addTimetable(University university, Timetable timetable) {
//        timetable.setUniversity(university);
//
//        timetableService.updateTimetable(timetable);
//    }
//
//    public void removeTimetable(University universityFind, Timetable timetable) {
//        University university = universityRepository.findById(universityFind.getId()).orElseThrow();
//        university.getTimetableSet().remove(timetable);
//
//        universityRepository.save(university);
//    }
//
//    public void addStudent(University university, Student student) {
//        student.setUniversity(university);
//
//        studentService.updateStudent(student);
//    }
//
//    public void removeStudent(University universityFind, Student student) {
//        University university = universityRepository.findById(universityFind.getId()).orElseThrow();
//        university.getStudentSet().remove(student);
//
//        universityRepository.save(university);
//    }
//
//    public void addGroup(University university, Group group) {
//        group.setUniversity(university);
//
//        groupService.updateGroup(group);
//    }
//
//    public void removeGroup(University universityFind, Group group) {
//        University university = universityRepository.findById(universityFind.getId()).orElseThrow();
//        university.getGroupSet().remove(group);
//
//        universityRepository.save(university);
//    }
//
//    public void addTeacher (University university, Teacher teacher) {
//        teacher.setUniversity(university);
//
//        teacherService.updateTeacher(teacher);
//    }
//
//    public void removeTeacher(University universityFind, Teacher teacher) {
//        University university = universityRepository.findById(universityFind.getId()).orElseThrow();
//        university.getTeacherSet().remove(teacher);
//
//        universityRepository.save(university);
//    }
//
//    public void addLesson(University university, Lesson lesson) {
//        lesson.setUniversity(university);
//
//        lessonService.updateLesson(lesson);
//    }
//
//    public void removeLesson(University universityFind, Lesson lesson) {
//        University university = universityRepository.findById(universityFind.getId()).orElseThrow();
//        university.getLessonSet().remove(lesson);
//
//        universityRepository.save(university);
//    }
}
