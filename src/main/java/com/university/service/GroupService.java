package com.university.service;

import com.university.model.Group;
import com.university.model.Student;
import com.university.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final StudentService studentService;

    public void createGroup(Group group) {
        groupRepository.save(group);
    }

    public void createGroupFromList(List<Group> groupList) {
        groupRepository.saveAll(groupList);
    }

    public List<Group> findAllGroup() {
        return groupRepository.findAll();
    }

    public Group findGroupById(Long idGroup) {
        return groupRepository.findById(idGroup).orElseThrow();
    }

    public void updateGroup(Group group) {
        Group findGroupById = findGroupById(group.getId());
        findGroupById.setNameGroup(group.getNameGroup());
        findGroupById.setUniversity(group.getUniversity());

        groupRepository.save(findGroupById);
    }

    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    public void addStudentToGroup(Group group, Student student) {
        student.setGroup(group);

        studentService.updateStudent(student);
    }
}
