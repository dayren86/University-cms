package com.university.service;

import com.university.model.Lesson;
import com.university.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public void createLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void createLessonFromList(List<Lesson> lessonList) {
        lessonRepository.saveAll(lessonList);
    }

    public List<Lesson> findAllLesson() {
        return lessonRepository.findAll();
    }

    public Lesson findLessonById(Long idLesson) {
        return lessonRepository.findById(idLesson).orElseThrow();
    }

    public void updateLesson(Lesson lesson) {
        Lesson findLessonById = findLessonById(lesson.getId());
        findLessonById.setNameLesson(lesson.getNameLesson());
        findLessonById.setAudience(lesson.getAudience());
        findLessonById.setTeacher(lesson.getTeacher());
        findLessonById.setGroup(lesson.getGroup());
        findLessonById.setUniversity(lesson.getUniversity());
        findLessonById.setTimetable(lesson.getTimetable());

        lessonRepository.save(findLessonById);
    }

    public void deleteLesson(Lesson lesson) {
        lessonRepository.delete(lesson);
    }
}
