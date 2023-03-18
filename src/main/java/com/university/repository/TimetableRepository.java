package com.university.repository;

import com.university.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    @Query(value = "SELECT timetable.id, timetable.local_date, timetable.student_id, timetable.teacher_id, timetable.university_id FROM timetable " +
            "JOIN lesson ON lesson.timetable_id = timetable.id " +
            "JOIN teacher ON lesson.teacher_id = teacher.id " +
            "WHERE teacher.id = :teacherId", nativeQuery = true)
    List<Timetable> findTimetableFotTeacher(@Param("teacherId") Long teacherId) throws SQLException;
}
