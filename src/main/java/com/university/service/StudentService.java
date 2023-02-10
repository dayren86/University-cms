package com.university.service;

import com.university.model.Student;
import com.university.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public void createStudentFromList(List<Student> studentList) {
        studentRepository.saveAll(studentList);
    }

    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    public Student findStudentById(Long idStudent) {
        return studentRepository.findById(idStudent).orElseThrow();
    }

    public void updateStudent(Student student) {
        Student findStudentById = findStudentById(student.getId());
        findStudentById.setFirstName(student.getFirstName());
        findStudentById.setLastName(student.getLastName());
        findStudentById.setUniversity(student.getUniversity());
        findStudentById.setGroup(student.getGroup());

        studentRepository.save(findStudentById);
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }
}
