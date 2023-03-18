package com.university.model;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nameUniversity;

    @OneToMany(mappedBy = "university", fetch = FetchType.EAGER)
    private Set<Timetable> timetableSet;

    @OneToMany(mappedBy = "university", fetch = FetchType.EAGER)
    private Set<Student> studentSet;

    @OneToMany(mappedBy = "university", fetch = FetchType.EAGER)
    private Set<Teacher> teacherSet;

    @OneToMany(mappedBy = "university", fetch = FetchType.EAGER)
    private Set<Lesson> lessonSet;

    @OneToMany(mappedBy = "university", fetch = FetchType.EAGER)
    private Set<Group> groupSet;

    public University(long id) {
        this.id = id;
    }

    public University() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameUniversity() {
        return nameUniversity;
    }

    public void setNameUniversity(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }

    public Set<Timetable> getTimetableSet() {
        return timetableSet;
    }

    public void setTimetableSet(Set<Timetable> timetableSet) {
        this.timetableSet = timetableSet;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }

    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet = lessonSet;
    }

    public Set<Group> getGroupSet() {
        return groupSet;
    }

    public void setGroupSet(Set<Group> groupSet) {
        this.groupSet = groupSet;
    }
}
