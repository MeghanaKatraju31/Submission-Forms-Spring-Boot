package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ControllerclassSubmission.class)
public class TableclassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TableclassRepo repo;

    @InjectMocks
    private ControllerclassSubmission controllerclassSubmission;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPostDetails_WithoutErrors() throws Exception {
        Tableclass tableclass = new Tableclass();
        tableclass.setCid(123456); // Valid CID
        tableclass.setCname("John Doe");
        tableclass.setCemail("john.doe@example.com");
        tableclass.setDateOfBirth("1990-01-01");
        tableclass.setTimezone("CST");
        tableclass.setTime("12:00");
        tableclass.setAmpm("AM");
        tableclass.setCountry("USA");
        tableclass.setState("TX");

        when(repo.save(any(Tableclass.class))).thenReturn(tableclass);

        mockMvc.perform(post("/details")
                .flashAttr("tableclass", tableclass))
                .andExpect(status().isOk())
                .andExpect(view().name("NewFile1"))
                .andExpect(model().attributeExists("tableclass"))
                .andExpect(model().attribute("tableclass", tableclass));

        verify(repo).save(any(Tableclass.class));
    }

    @Test
    public void testPostDetails_WithErrors() throws Exception {
        Tableclass tableclass = new Tableclass();
        tableclass.setCid(null); // Invalid CID (null value to trigger validation error)
        tableclass.setCname(""); // Empty name to trigger validation error

        mockMvc.perform(post("/details")
                .flashAttr("tableclass", tableclass))
                .andExpect(status().isOk())
                .andExpect(view().name("NewFile"))
                .andExpect(model().attributeHasErrors("tableclass"));
    }
}
