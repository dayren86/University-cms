package com.university.model;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nameTeacher;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private Set<Timetable> timetableSet;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public Set<Timetable> getTimetableSet() {
        return timetableSet;
    }

    public void setTimetableSet(Set<Timetable> timetableSet) {
        this.timetableSet = timetableSet;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", nameTeacher='" + nameTeacher + '\'' +
                ", timetableSet=" + timetableSet +
                ", university=" + university +
                '}';
    }
}
