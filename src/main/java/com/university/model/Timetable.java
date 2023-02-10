package com.university.model;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "timetable")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate localDate;

    @OneToMany(mappedBy = "timetable", fetch = FetchType.EAGER)
    private Set<Lesson> lessonSet;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet = lessonSet;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "id=" + id +
                ", localDate=" + localDate +
                ", lessonSet=" + lessonSet +
                ", university=" + university +
                ", student=" + student +
                ", teacher=" + teacher +
                '}';
    }
}
