package com.university;

import com.university.model.Student;
import com.university.service.StudentService;
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
class StudentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    @Test
    void studentTest_checkTemplate() throws Exception {
        mvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("studentList"))
                .andExpect(view().name("student"));
    }

    @Test
    void createStudentMVCTest_checkTemplate() throws Exception {
        mvc.perform(get("/student/template"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("universityList"))
                .andExpect(model().attributeExists("groupList"))
                .andExpect(view().name("studentcreate"));
    }

    @Test
    void createStudentTest_checkRedirectEntity() throws Exception {
        Student student = new Student();
        student.setId(1);
        student.setFirstName("test");

        mvc.perform(post("/student").header("student", student) )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student"))
                .andExpect(result -> assertEquals(student.toString(), result.getRequest().getHeader("student")));
    }

    @Test
    void deleteStudentTest_checkPathVariable() throws Exception {
        mvc.perform(post("/student/{id}",1L))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(result -> assertEquals("/student/1", result.getRequest().getRequestURI()));
    }

    @Test
    void updateStudentMVCTest_checkEntityTemplateQuery() throws Exception {
        Student student = new Student();
        student.setId(1);
        student.setFirstName("test");
        student.setLastName("last name");

        when(studentService.findStudentById(1L)).thenReturn(student);

        mvc.perform(get("/student/changetemplate/{id}",1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("studentupdate"))
                .andExpect(model().attributeExists("studentId"))
                .andExpect(model().attributeExists("studentFirstName"))
                .andExpect(model().attributeExists("studentLastName"))
                .andExpect(result -> assertEquals("/student/changetemplate/1", result.getRequest().getRequestURI()));
    }

    @Test
    void updateStudentTest_checkQuery() throws Exception {
        Student student = new Student();
        student.setId(1);
        student.setFirstName("test");
        student.setLastName("last name");

        mvc.perform(post("/student/update").header("student", student) )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student"))
                .andExpect(result -> assertEquals(student.toString(), result.getRequest().getHeader("student")));
    }
}
