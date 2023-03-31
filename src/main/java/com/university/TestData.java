package com.university;

import com.university.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Slf4j
@Service
public class TestData {
    public List<Group> generateGroups() {
        List<String> stringList = Arrays.asList("ds-12", "rf-45", "ql-92", "of-64", "jf-62", "td-17", "pb-84", "tm-79", "eg-34", "ih-49", "je-74");

        List<Group> groupList = new ArrayList<>();
        for (String nameGroup : stringList) {
            Group group = new Group();
            group.setNameGroup(nameGroup);
            group.setUniversity(new University(1));
            groupList.add(group);
        }

        log.info("Generate groups. Size: " + groupList.size());

        return groupList;
    }

    public List<Student> generateStudents() {
        List<String> firstNameList = Arrays.asList("Jim", "Fred", "Baz", "Bing", "Lachlan ", "Fred", "Allan", "Cindy", "Steffan", "Alyssa", "Gertrude", "Kelsey", "Estelle", "Seamus", "Genevieve", "Irene", "Karina", "Tammy", "Diana", "Tiago");
        List<String> lastNameList = Arrays.asList("Duck", "Swan", "Cooper", "Bing", "Christensen", "Chapman", "Mays", "Oneal", "Bradford", "Vang", "Cisneros", "Hatfield", "Connolly", "Bridges", "Larsen", "Jacobson", "Chang", "Hooper", "Foley", "Frazier");

        Set<Student> studentsList = new HashSet<>();

        for (String firstName : firstNameList) {
            for (int i = 0; i < lastNameList.size(); i++) {

                if (studentsList.size() != 20) {
                    Student students = new Student();
                    students.setFirstName(firstName);
                    students.setLastName(lastNameList.get(i));
                    students.setUniversity(new University(1));
                    studentsList.add(students);
                }
            }
        }
        ArrayList<Student> students = new ArrayList<>(studentsList);

        log.info("Generate student. Size: " + students.size());

        return students;
    }

    public List<Teacher> generateTeacher() {
        List<String> stringList = Arrays.asList("Baz", "Allan", "Kelsey", "Seamus", "Genevieve", "Tiago");

        List<Teacher> teacherList = new ArrayList<>();
        for (String nameTeacher : stringList) {
            Teacher teacher = new Teacher();
            teacher.setNameTeacher(nameTeacher);
            teacher.setUniversity(new University(1));
            teacherList.add(teacher);
        }

        log.info("Generate teacher. Size: " + teacherList.size());

        return teacherList;
    }

    public List<Lesson> generateLesson() {
        List<String> nameLessonList = Arrays.asList("Math", "Biology", "World history", "Geometry", "Physics");
        List<LocalTime> localTimeList = Arrays.asList(LocalTime.of(12,00), LocalTime.of(13,00), LocalTime.of(14,00), LocalTime.of(15,00), LocalTime.of(16,00));
        List<Integer> audienceList = Arrays.asList(5, 7, 9, 6, 10);

        List<Lesson> lessonList = new ArrayList<>();

        for (int i = 0; i < nameLessonList.size(); i++) {
            Lesson lesson = new Lesson();
            lesson.setNameLesson(nameLessonList.get(i));
            lesson.setTime(localTimeList.get(i));
            lesson.setAudience(audienceList.get(i));
            lesson.setUniversity(new University(1));
            lessonList.add(lesson);
        }

        return lessonList;
    }
    public List<Timetable> generateTimetable() {
        List<LocalDate> timetableDateList = Arrays.asList(LocalDate.of(2023,02,02),
                LocalDate.of(2023, 02,10),
                LocalDate.of(2023, 02, 20));

        List<Timetable> timetableList = new ArrayList<>();

        for (LocalDate localDate : timetableDateList) {
            Timetable timetable = new Timetable();
            timetable.setLocalDate(localDate);
            timetable.setUniversity(new University(1));
            timetableList.add(timetable);
        }

        return timetableList;
    }

    public List<Roles> generateRoles() {
        return List.of(new Roles("ADMIN"), new Roles("STUDENT"), new Roles("TEACHER"));
    }

    public List<Users> generateUsers() {
        return List.of(new Users("admin@gmail.com", "admin", "Admin user", new Roles(1)),
                new Users("student@gmail.com", "student", "Student user", new Roles(2)),
                new Users("teacher@gmail.com", "teacher", "Teacher user", new Roles(3))
                );
    }
}
