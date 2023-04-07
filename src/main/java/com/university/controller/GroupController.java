package com.university.controller;

import com.university.model.Group;
import com.university.service.GroupService;
import com.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RequestMapping("/group")
@Controller
@RolesAllowed({"ADMIN"})
public class GroupController {

    private final GroupService groupService;
    private final UniversityService universityService;

    @GetMapping
    @RolesAllowed({"TEACHER", "ADMIN"})
    public ModelAndView group() {
        ModelAndView groupModelAndView = new ModelAndView("group");
        groupModelAndView.addObject("groupList", groupService.findAllGroup());

        return groupModelAndView;
    }

    @GetMapping("/template")
    public ModelAndView createGroupMVC() {
        ModelAndView groupMVC = new ModelAndView("groupcreate");
        groupMVC.addObject("universityList", universityService.findAllTimetable());

        return groupMVC;
    }

    @PostMapping()
    public String createGroup(@ModelAttribute(name = "group") Group group) {
        groupService.createGroup(group);

        return "redirect:/group";
    }

    @PostMapping("/{id}")
    public String deleteGroup(@PathVariable("id") long id) {
        groupService.deleteGroup(groupService.findGroupById(id));

        return "redirect:/group";
    }

    @GetMapping("/changetemplate/{id}")
    public ModelAndView updateGroupMVC(@PathVariable("id") long id) {
        Group groupById = groupService.findGroupById(id);

        ModelAndView studentUpdateModelAndView = new ModelAndView("groupupdate");
        studentUpdateModelAndView.addObject("groupId", groupById.getId());
        studentUpdateModelAndView.addObject("groupName", groupById.getNameGroup());
        studentUpdateModelAndView.addObject("universityList", universityService.findAllTimetable());

        return studentUpdateModelAndView;
    }

    @PostMapping("/update")
    public String updateGroup(@ModelAttribute(name = "group") Group group) {
        groupService.updateGroup(group);

        return "redirect:/group";
    }
}
