package com.university;

import com.university.model.*;
import com.university.repository.UniversityRepository;
import com.university.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	private final TestData testData;

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		University university = new University();
		university.setNameUniversity("VNTU");
		universityService.createUniversity(university);

		studentService.createStudentFromList(testData.generateStudents());
		groupService.createGroupFromList(testData.generateGroups());
		teacherService.createTeacherFromList(testData.generateTeacher());
		lessonService.createLessonFromList(testData.generateLesson());
		timetableService.createTimetableFromList(testData.generateTimetable());
	}
}
