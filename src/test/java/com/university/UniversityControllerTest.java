package com.university;

import com.university.model.University;
import com.university.service.UniversityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UniversityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UniversityService universityService;

    @Test
    void universityTest_checkTemplate() throws Exception {
        mvc.perform(get("/university"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("universityList"))
                .andExpect(view().name("university"));
    }

    @Test
    void universityCreateMVCTest_checkTemplate() throws Exception {
        mvc.perform(get("/university/template"))
                .andExpect(status().isOk())
                .andExpect(view().name("universitycreate"));
    }

    @Test
    void universityCreateTest_checkRedirectEntity() throws Exception {
        University university = new University();
        university.setId(1);
        university.setNameUniversity("test");

        mvc.perform(post("/university").header("university", university))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/university"))
                .andExpect(result -> assertEquals(university.toString(), result.getRequest().getHeader("university")));
    }
}
