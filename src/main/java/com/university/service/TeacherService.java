package com.university.service;

import com.university.model.Teacher;
import com.university.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public void createTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void createTeacherFromList(List<Teacher> teacherList) {
        teacherRepository.saveAll(teacherList);
    }

    public List<Teacher> findAllTeacher() {
        return teacherRepository.findAll();
    }

    public Teacher findTeacherById(Long idTeacher) {
        return teacherRepository.findById(idTeacher).orElseThrow();
    }

    public void updateTeacher(Teacher teacher) {
        Teacher findTeacherById = findTeacherById(teacher.getId());
        findTeacherById.setNameTeacher(teacher.getNameTeacher());
        findTeacherById.setUniversity(teacher.getUniversity());

        teacherRepository.save(findTeacherById);
    }

    public void deleteTeacher(Teacher teacher) {
        teacherRepository.delete(teacher);
    }
}
