package com.university.service;

import com.university.model.Timetable;
import com.university.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableService {
    private final TimetableRepository timetableRepository;

    public void createTimetable(Timetable timetable) {
        timetableRepository.save(timetable);
    }

    public void createTimetableFromList(List<Timetable> timetableList) {
        timetableRepository.saveAll(timetableList);
    }

    public List<Timetable> findAllTimetable() {
        return timetableRepository.findAll();
    }

    public Timetable findTimetableById(Long idTimetable) {
        return timetableRepository.findById(idTimetable).orElseThrow();
    }

    public void updateTimetable(Timetable timetable) {
        Timetable findTimetableById = findTimetableById(timetable.getId());
        findTimetableById.setLocalDate(timetable.getLocalDate());
        findTimetableById.setStudent(timetable.getStudent());
        findTimetableById.setTeacher(timetable.getTeacher());
        findTimetableById.setUniversity(timetable.getUniversity());

        timetableRepository.save(findTimetableById);
    }

    public void deleteTimetable(Timetable timetable) {
        timetableRepository.delete(timetable);
    }
}
