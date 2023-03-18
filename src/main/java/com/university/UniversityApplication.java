package com.university;

import com.university.model.*;
import com.university.repository.UniversityRepository;
import com.university.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class UniversityApplication implements CommandLineRunner {
//
//	private final UniversityService universityService;
//	private final StudentService studentService;
//	private final TimetableService timetableService;
//	private final GroupService groupService;
//	private final TeacherService teacherService;
//	private final LessonService lessonService;
//	private final UniversityRepository universityRepository;
//	private final TestData testData;

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		University university = new University();
//		university.setNameUniversity("VNTU");
//		universityService.createUniversity(university);
//
//		groupService.createGroupFromList(testData.generateGroups());
//		studentService.createStudentFromList(testData.generateStudents());
//		teacherService.createTeacherFromList(testData.generateTeacher());
//		lessonService.createLessonFromList(testData.generateLesson());
//		timetableService.createTimetableFromList(testData.generateTimetable());
//
//		List<Student> allStudent = studentService.findAllStudent();
//		List<Group> allGroup = groupService.findAllGroup();
//		for (int i = 0; i < allStudent.size(); i++) {
//			Student student = allStudent.get(i);
//			student.setGroup(allGroup.get((int) (1 + Math.random() * 10)));
//
//			studentService.updateStudent(student);
//		}
//
//		List<Lesson> allLesson = lessonService.findAllLesson();
//		List<Teacher> allTeacher = teacherService.findAllTeacher();
//		List<Timetable> allTimetable = timetableService.findAllTimetable();
//		for (int i = 0; i < allLesson.size(); i++) {
//			Lesson lesson = allLesson.get(i);
//			lesson.setGroup(allGroup.get((int) (0 + Math.random() * 10)));
//			lesson.setTeacher(allTeacher.get((int) (0 + Math.random() * 6)));
//			lesson.setTimetable(allTimetable.get((int) (0 + Math.random() * 3)));
//
//			lessonService.updateLesson(lesson);
//		}
	}
}
