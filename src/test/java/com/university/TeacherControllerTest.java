package com.university;

import com.university.model.Teacher;
import com.university.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TeacherControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    void teacherTest_checkTemplate() throws Exception {
        mvc.perform(get("/teacher"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("teacherList"))
                .andExpect(view().name("teacher"));
    }

    @Test
    void createTeacherMvcTest_checkTemplate() throws Exception {
        mvc.perform(get("/teacher/template"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("universityList"))
                .andExpect(view().name("teachercreate"));
    }

    @Test
    void createGroupTest_checkRedirectEntity() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setNameTeacher("test");

        mvc.perform(post("/teacher").header("teacher", teacher) )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/teacher"))
                .andExpect(result -> assertEquals(teacher.toString(), result.getRequest().getHeader("teacher")));
    }

    @Test
    void deleteTeacherTest_checkPathVariable() throws Exception {
        mvc.perform(post("/teacher/{id}",1L))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(result -> assertEquals("/teacher/1", result.getRequest().getRequestURI()));
    }

    @Test
    void updateTeacherMVCTest_checkEntityTemplateQuery() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setNameTeacher("test");

        when(teacherService.findTeacherById(1L)).thenReturn(teacher);

        mvc.perform(get("/teacher/changetemplate/{id}",1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("teacherupdate"))
                .andExpect(model().attributeExists("teacherId"))
                .andExpect(model().attributeExists("teacherName"))
                .andExpect(model().attributeExists("universityList"))
                .andExpect(result -> assertEquals("/teacher/changetemplate/1", result.getRequest().getRequestURI()));
    }

    @Test
    void updateGroupTest_checkQuery() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setNameTeacher("test");

        mvc.perform(post("/teacher/update").header("teacher", teacher) )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/teacher"))
                .andExpect(result -> assertEquals(teacher.toString(), result.getRequest().getHeader("teacher")));
    }
}
