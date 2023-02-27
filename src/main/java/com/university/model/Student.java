package com.university.model;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Timetable> timetableSet;

    @ManyToOne
    @JoinColumn(name = "groups_id")
    private Group group;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Set<Timetable> getTimetableSet() {
        return timetableSet;
    }

    public void setTimetableSet(Set<Timetable> timetableSet) {
        this.timetableSet = timetableSet;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
//
//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", university=" + university.getNameUniversity() +
//                ", timetableSet=" + timetableSet +
//                ", group=" + group.getId() + " " + group.getNameGroup() +
//                '}';
//    }
}
