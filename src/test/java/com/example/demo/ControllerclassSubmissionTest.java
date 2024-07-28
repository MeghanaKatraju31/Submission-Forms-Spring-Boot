package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ControllerclassSubmission.class)
public class ControllerclassSubmissionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TableclassRepo repo;

    @BeforeEach
    void setUp() {
        // Set up necessary data or state before each test, if needed
    }

    @Test
    void testShowForm() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("NewFile"))
                .andExpect(model().attributeExists("tableclass"));
    }

    @Test
    void testPostDetails_WithoutErrors() throws Exception {
        Tableclass tableclass = new Tableclass();
        tableclass.setCid(123); // Adjust according to your requirements
        tableclass.setCname("Test Name");
        tableclass.setCemail("test@example.com");
        tableclass.setDateOfBirth("2000-01-01");
        tableclass.setTimezone("UTC");
        tableclass.setTime("12:00");
        
        mockMvc.perform(post("/details")
                .flashAttr("tableclass", tableclass))
                .andExpect(status().isOk())
                .andExpect(view().name("NewFile1"))
                .andExpect(model().attribute("tableclass", tableclass));
    }

    @Test
    void testPostDetails_WithErrors() throws Exception {
        Tableclass tableclass = new Tableclass();
        // Leave fields empty or invalid to trigger validation errors
        
        mockMvc.perform(post("/details")
                .flashAttr("tableclass", tableclass))
                .andExpect(status().isOk())
                .andExpect(view().name("NewFile"))
                .andExpect(model().attributeHasFieldErrors("tableclass", "cid", "cname", "cemail")); // Adjust based on actual error messages
    }
}

