package com.university;

import com.university.model.Group;
import com.university.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GroupService groupService;

    @Test
    void groupTest_checkTemplate() throws Exception {
        mvc.perform(get("/group"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("groupList"))
                .andExpect(view().name("group"));
    }

    @Test
    void createGroupMVCTest_checkTemplate() throws Exception {
        mvc.perform(get("/group/template"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("universityList"))
                .andExpect(view().name("groupcreate"));
    }

    @Test
    void createGroupTest_checkRedirectEntity() throws Exception {
        Group group = new Group();
        group.setId(1L);
        group.setNameGroup("test");

        mvc.perform(post("/group").header("group", group) )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/group"))
                .andExpect(result -> assertEquals(group.toString(), result.getRequest().getHeader("group")));
    }

    @Test
    void deleteGroupTest_checkPathVariable() throws Exception {
        mvc.perform(post("/group/{id}",1L))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(result -> assertEquals("/group/1", result.getRequest().getRequestURI()));
    }

    @Test
    void updateGroupMVCTest_checkEntityTemplateQuery() throws Exception {
        Group group = new Group();
        group.setId(1L);
        group.setNameGroup("test");

        when(groupService.findGroupById(1L)).thenReturn(group);

        mvc.perform(get("/group/changetemplate/{id}",1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("groupupdate"))
                .andExpect(model().attributeExists("groupId"))
                .andExpect(model().attributeExists("groupName"))
                .andExpect(model().attributeExists("universityList"))
                .andExpect(result -> assertEquals("/group/changetemplate/1", result.getRequest().getRequestURI()));
    }

    @Test
    void updateGroupTest_checkQuery() throws Exception {
        Group group = new Group();
        group.setId(1L);
        group.setNameGroup("test");

        mvc.perform(post("/group/update").header("group", group) )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/group"))
                .andExpect(result -> assertEquals(group.toString(), result.getRequest().getHeader("group")));
    }
}
