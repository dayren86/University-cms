package com.university.model;

import javax.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    private String nameLesson;

    private Time time;

    private Integer audience;

    @OneToOne
    private Teacher teacher;

    @OneToOne
    private Group group;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne
    @JoinColumn(name = "timetable_id")
    private Timetable timetable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameLesson() {
        return nameLesson;
    }

    public void setNameLesson(String nameLesson) {
        this.nameLesson = nameLesson;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getAudience() {
        return audience;
    }

    public void setAudience(Integer audience) {
        this.audience = audience;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", nameLesson='" + nameLesson + '\'' +
                ", time=" + time +
                ", audience=" + audience +
                ", teacher=" + teacher +
                ", group=" + group +
                ", university=" + university +
                ", timetable=" + timetable +
                '}';
    }
}