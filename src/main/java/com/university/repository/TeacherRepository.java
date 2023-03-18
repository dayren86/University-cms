package com.university.repository;

import com.university.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT teacher.id, teacher.name_teacher, timetable.id, teacher.university_id FROM timetable " +
            "JOIN lesson ON lesson.timetable_id = timetable.id " +
            "JOIN teacher ON lesson.teacher_id = teacher.id ",nativeQuery = true)
    List<Teacher> findAllTeacherAndTimetable();
}
