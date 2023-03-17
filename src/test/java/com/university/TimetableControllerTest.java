package com.university;

import com.university.model.Timetable;
import com.university.service.TimetableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
class TimetableControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TimetableService timetableService;

    @Test
    void timetableTest_checkTemplate() throws Exception {
        mvc.perform(get("/timetable"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("timetableList"))
                .andExpect(view().name("timetable"));
    }

    @Test
    void timetableCreateMVCTest_checkTemplate() throws Exception {
        mvc.perform(get("/timetable/template"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("universityList"))
                .andExpect(view().name("timetablecreate"));
    }

    @Test
    void timetableCreateTest_checkRedirectEntity() throws Exception {
        Timetable timetable = new Timetable();
        timetable.setId(1);

        mvc.perform(post("/timetable").header("timetable", timetable).param("dateString", LocalDate.now().toString()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/timetable"))
                .andExpect(result -> assertEquals(timetable.toString(), result.getRequest().getHeader("timetable")));
    }

    @Test
    void timetableDeleteTest_checkPathVariable() throws Exception {
        mvc.perform(post("/timetable/{id}",1L))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(result -> assertEquals("/timetable/1", result.getRequest().getRequestURI()));
    }

    @Test
    void timetableUpdateMVCTest_checkEntityTemplateQuery() throws Exception {
        Timetable timetable = new Timetable();
        timetable.setId(1);

        when(timetableService.findTimetableById(1L)).thenReturn(timetable);

        mvc.perform(get("/timetable/changetemplate/{id}",1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("timetableupdate"))
                .andExpect(model().attributeExists("timetableId"))
                .andExpect(result -> assertEquals("/timetable/changetemplate/1", result.getRequest().getRequestURI()));
    }
}
