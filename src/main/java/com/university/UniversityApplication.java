package com.university;

import com.university.model.*;
import com.university.repository.UniversityRepository;
import com.university.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class UniversityApplication implements CommandLineRunner {

	private final UniversityService universityService;
	private final StudentService studentService;
	private final TimetableService timetableService;
	private final GroupService groupService;
	private final TeacherService teacherService;
	private final LessonService lessonService;
	private final UniversityRepository universityRepository;

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		University university = new University();
		university.setNameUniversity("vntu");
		universityService.createUniversity(university);

		Student student = new Student();
		student.setFirstName("Vasya");
		studentService.createStudent(student);

		Student student2 = new Student();
		student2.setFirstName("Vasya2");
		studentService.createStudent(student2);

		Lesson lesson = new Lesson();
		lesson.setNameLesson("Math");
		lessonService.createLesson(lesson);

		Teacher teacher = new Teacher();
		teacher.setNameTeacher("Jann");
		teacherService.createTeacher(teacher);

		Group group = new Group();
		group.setNameGroup("group1");
		groupService.createGroup(group);

		Timetable timetable = new Timetable();
		timetable.setLocalDate(LocalDate.of(2023, 2, 9));
		timetableService.createTimetable(timetable);

		universityService.addStudent(university, student);
		universityService.addStudent(university, student2);

		universityService.addGroup(university, group);

		universityService.addLesson(university, lesson);

		universityService.addTeacher(university, teacher);

		universityService.addTimetable(university, timetable);

		groupService.addStudentToGroup(group, student);
		groupService.addStudentToGroup(group, student2);

		timetableService.addLesson(timetable, lesson);

		University universityFind = universityRepository.findById(1L).get();
		universityFind.getStudentSet().forEach(student1 -> System.out.println(student1));
		universityFind.getTeacherSet().forEach(teacher1 -> System.out.println(teacher1));
		universityFind.getTimetableSet().forEach(timetable1 -> System.out.println(timetable1.getLocalDate()+ "\n" + timetable1.getLessonSet()));
		universityFind.getLessonSet().forEach(lesson1 -> System.out.println(lesson1));
		universityFind.getGroupSet().forEach(group1 -> System.out.println(group1.getNameGroup() + "\n" + group1.getStudentSet()));

	}
}
