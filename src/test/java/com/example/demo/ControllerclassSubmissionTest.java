package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

@WebMvcTest(ControllerclassSubmission.class)
public class ControllerclassSubmissionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TableclassRepo repo;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ControllerclassSubmission controllerclassSubmission;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controllerclassSubmission).build();
    }

    @Test
    public void testShowForm() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("NewFile"))
            .andExpect(model().attributeExists("tableclass"));
    }

    @Test
    public void testPostDetails_WithErrors() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);

        mockMvc.perform(post("/details")
                .flashAttr("tableclass", new Tableclass()))
            .andExpect(status().isOk())
            .andExpect(view().name("NewFile"));

        verify(repo, org.mockito.Mockito.times(0)).save(any(Tableclass.class));
    }

    @Test
    public void testPostDetails_WithoutErrors() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(false);

        Tableclass tableclass = new Tableclass();
        tableclass.setCid("abc123");
        tableclass.setCname("John Doe");
        tableclass.setCemail("john.doe@example.com");
        tableclass.setDateOfBirth("2000-01-01");
        tableclass.setTimezone("GMT");
        tableclass.setTime("12:00");
        tableclass.setAmpm("PM");

        mockMvc.perform(post("/details")
                .flashAttr("tableclass", tableclass))
            .andExpect(status().isOk())
            .andExpect(view().name("NewFile1"))
            .andExpect(model().attribute("cid", tableclass.getCid()))
            .andExpect(model().attribute("cname", tableclass.getCname()))
            .andExpect(model().attribute("cemail", tableclass.getCemail()))
            .andExpect(model().attribute("dateOfBirth", tableclass.getDateOfBirth()))
            .andExpect(model().attribute("timezone", tableclass.getTimezone()))
            .andExpect(model().attribute("time", tableclass.getTime()))
            .andExpect(model().attribute("ampm", tableclass.getAmpm()));

        verify(repo).save(any(Tableclass.class));
    }
}
