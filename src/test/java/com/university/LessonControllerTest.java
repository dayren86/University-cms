package com.university;

import com.university.model.Lesson;
import com.university.service.LessonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LessonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LessonService lessonService;

    @Test
    void lessonTest_checkTemplate() throws Exception {
        mvc.perform(get("/lesson"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("lessonList"))
                .andExpect(view().name("lesson"));
    }

    @Test
    void createGroupMVCTest_checkTemplate() throws Exception {
        mvc.perform(get("/lesson/template"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("teacherList"))
                .andExpect(model().attributeExists("groupList"))
                .andExpect(model().attributeExists("timetableList"))
                .andExpect(model().attributeExists("universityList"))
                .andExpect(view().name("lessoncreate"));
    }

    @Test
    void createLessonTest_checkRedirectEntity() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setId(1);
        lesson.setNameLesson("test");

        mvc.perform(post("/lesson").header("lesson", lesson).param("timeString", LocalTime.now().toString()) )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/lesson"))
                .andExpect(result -> assertEquals(lesson.toString(), result.getRequest().getHeader("lesson")));
    }

    @Test
    void deleteLessonTest_checkPathVariable() throws Exception {
        mvc.perform(post("/lesson/{id}",1L))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(result -> assertEquals("/lesson/1", result.getRequest().getRequestURI()));
    }

    @Test
    void updateLessonMVCTest_checkEntityTemplateQuery() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setId(1);
        lesson.setNameLesson("test");

        when(lessonService.findLessonById(1L)).thenReturn(lesson);

        mvc.perform(get("/lesson/changetemplate/{id}",1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("lessonupdate"))
                .andExpect(model().attributeExists("lessonId"))
                .andExpect(model().attributeExists("lessonName"))
                .andExpect(result -> assertEquals("/lesson/changetemplate/1", result.getRequest().getRequestURI()));
    }

    @Test
    void updateLessonTest_checkQuery() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setId(1);
        lesson.setNameLesson("test");

        mvc.perform(post("/lesson/update").header("lesson", lesson) )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/lesson"))
                .andExpect(result -> assertEquals(lesson.toString(), result.getRequest().getHeader("lesson")));
    }
}
