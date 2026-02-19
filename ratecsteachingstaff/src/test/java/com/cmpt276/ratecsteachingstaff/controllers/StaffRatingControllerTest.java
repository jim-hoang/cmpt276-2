package com.cmpt276.ratecsteachingstaff.controllers;

import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.cmpt276.ratecsteachingstaff.models.StaffRating;
import com.cmpt276.ratecsteachingstaff.models.StaffRatingFactory;
import com.cmpt276.ratecsteachingstaff.repository.StaffRatingRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class StaffRatingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StaffRatingRepository staffRatingRepository;

    @Test
    void testAddRatingSaves() throws Exception {
      mockMvc.perform(post("/ratings/add")
            .param("name", "John Doe")
            .param("email", "john@email.com")
            .param("comment", "great")
            .param("role", "professor")
            .param("clarity", "4")
            .param("niceness", "5")
            .param("knowledgeableScore", "6"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        verify(staffRatingRepository).save(any(StaffRating.class));
    }
    
    @Test
    void testGetAddPage() throws Exception {
        mockMvc.perform(get("/ratings/add"))
            .andExpect(status().isOk());
    }

    @Test
    void testHomePage() throws Exception {
        StaffRating testRating1 = StaffRatingFactory.createRating("John Doe", "john@email.com",
         4, 5, 6, "professor", "great");
        StaffRating testRating2 = StaffRatingFactory.createRating("Benny Homes", "benh@email.com",
         6, 3, 2, "ta", "awesome");

        when(staffRatingRepository.findAll()).thenReturn(List.of(testRating1, testRating2));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("ratings", hasSize(2)))
                .andExpect(model().attribute("ratings", hasItem(
                        allOf(
                                hasProperty("name", is(testRating1.getName())),
                                hasProperty("email", is(testRating1.getEmail())),
                                hasProperty("roleType", is(testRating1.getRoleType())),
                                hasProperty("clarity", is(testRating1.getClarity())),
                                hasProperty("niceness", is(testRating1.getNiceness())),
                                hasProperty("knowledgeableScore", is(testRating1.getKnowledgeableScore())),
                                hasProperty("comment", is(testRating1.getComment()))
                        )
                )))
                .andExpect(model().attribute("ratings", hasItem(
                        allOf(
                                hasProperty("name", is(testRating2.getName())),
                                hasProperty("email", is(testRating2.getEmail())),
                                hasProperty("roleType", is(testRating2.getRoleType())),
                                hasProperty("clarity", is(testRating2.getClarity())),
                                hasProperty("niceness", is(testRating2.getNiceness())),
                                hasProperty("knowledgeableScore", is(testRating2.getKnowledgeableScore())),
                                hasProperty("comment", is(testRating2.getComment()))
                        )
                )));
    }

    @Test
    void testGetRating() throws Exception {
        StaffRating testRating = StaffRatingFactory.createRating("John Doe", "john@email.com",
            4, 5, 6, "professor", "great");
        testRating.setId(1);

        when(staffRatingRepository.findById(1)).thenReturn(java.util.Optional.of(testRating));

        mockMvc.perform(get("/ratings/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"))
                .andExpect(model().attribute("rating", 
                        allOf(
                                hasProperty("id", is(testRating.getId())),
                                hasProperty("name", is(testRating.getName())),
                                hasProperty("email", is(testRating.getEmail())),
                                hasProperty("roleType", is(testRating.getRoleType())),
                                hasProperty("clarity", is(testRating.getClarity())),
                                hasProperty("niceness", is(testRating.getNiceness())),
                                hasProperty("knowledgeableScore", is(testRating.getKnowledgeableScore())),
                                hasProperty("comment", is(testRating.getComment()))
                        )
                ));
    }

    @Test
    void testEditRating() throws Exception {
        StaffRating testRating = StaffRatingFactory.createRating("Lenny Girez", "lennyg@email.com",
                6, 7, 8, "professor", "great");
        testRating.setId(1);

        when(staffRatingRepository.findById(1)).thenReturn(java.util.Optional.of(testRating));
        when(staffRatingRepository.save(any(StaffRating.class))).thenReturn(testRating);

        mockMvc.perform(post("/ratings/1/edit")
                        .param("name", "Some name")
                        .param("email", "myemail@email.com")
                        .param("comment", "excellent")
                        .param("role", "ta")
                        .param("clarity", "5")
                        .param("niceness", "6")
                        .param("knowledgeableScore", "7")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(staffRatingRepository).save(any(StaffRating.class));
    }

    @Test
    void testDeletePage() throws Exception {
        StaffRating testRating = StaffRatingFactory.createRating("johnny jones", "johnjones@email.com",
                9, 10, 7, "professor", "johnny is a good prof");
        testRating.setId(1);

        when(staffRatingRepository.findById(1)).thenReturn(java.util.Optional.of(testRating));

        mockMvc.perform(get("/ratings/1/delete"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"))
                .andExpect(model().attribute("rating", 
                        allOf(
                                hasProperty("id", is(testRating.getId())),
                                hasProperty("name", is(testRating.getName())),
                                hasProperty("email", is(testRating.getEmail())),
                                hasProperty("roleType", is(testRating.getRoleType())),
                                hasProperty("clarity", is(testRating.getClarity())),
                                hasProperty("niceness", is(testRating.getNiceness())),
                                hasProperty("knowledgeableScore", is(testRating.getKnowledgeableScore())),
                                hasProperty("comment", is(testRating.getComment()))
                        )
                ));
    }

    @Test
    void testDeleteRating() throws Exception {
        StaffRating testRating = StaffRatingFactory.createRating("Tam He", "tamhe@email.com",
                8, 8, 9, "ta", "cool ta");
        testRating.setId(1);

        when(staffRatingRepository.findById(1)).thenReturn(java.util.Optional.of(testRating));

        mockMvc.perform(post("/ratings/delete")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(staffRatingRepository).deleteById(1);
    }

    // Validation Tests
    @Test
    void testAddRating_invalidEmail() throws Exception {
        mockMvc.perform(post("/ratings/add")
                        .param("name", "John Doe")
                        .param("email", "john@gmail")
                        .param("comment", "great")
                        .param("role", "professor")
                        .param("clarity", "4")
                        .param("niceness", "5")
                        .param("knowledgeableScore", "6"))
                .andExpect(status().isOk())
                .andExpect(view().name("ratings/add"));

        verify(staffRatingRepository, never()).save(any(StaffRating.class));
    }

    @Test
    void testAddRating_missingEmail() throws Exception {
        mockMvc.perform(post("/ratings/add")
                        .param("name", "John Doe")
                        .param("email", "")
                        .param("comment", "great")
                        .param("role", "professor")
                        .param("clarity", "4")
                        .param("niceness", "5")
                        .param("knowledgeableScore", "6"))
                .andExpect(status().isOk())
                .andExpect(view().name("ratings/add"));

        verify(staffRatingRepository, never()).save(any(StaffRating.class));
    }

    @Test
    void testAddRating_missingName() throws Exception {
        mockMvc.perform(post("/ratings/add")
                        .param("name", "")
                        .param("email", "john@email.com")
                        .param("comment", "great")
                        .param("role", "professor")
                        .param("clarity", "4")
                        .param("niceness", "5")
                        .param("knowledgeableScore", "6"))
                .andExpect(status().isOk())
                .andExpect(view().name("ratings/add"));

        verify(staffRatingRepository, never()).save(any(StaffRating.class));
        
    }

    @Test
    void testAddRating_outOfRangeClarityScore() throws Exception {
        mockMvc.perform(post("/ratings/add")
                        .param("name", "")
                        .param("email", "john@email.com")
                        .param("comment", "great")
                        .param("role", "professor")
                        .param("clarity", "11")
                        .param("niceness", "5")
                        .param("knowledgeableScore", "6"))
                .andExpect(status().isOk())
                .andExpect(view().name("ratings/add"));

        verify(staffRatingRepository, never()).save(any(StaffRating.class));
    }

    @Test
    void testAddRating_outOfRangeNicenessScore() throws Exception {
        mockMvc.perform(post("/ratings/add")
                        .param("name", "")
                        .param("email", "john@email.com")
                        .param("comment", "great")
                        .param("role", "professor")
                        .param("clarity", "10")
                        .param("niceness", "100")
                        .param("knowledgeableScore", "6"))
                .andExpect(status().isOk())
                .andExpect(view().name("ratings/add"));

        verify(staffRatingRepository, never()).save(any(StaffRating.class));
    }

    @Test
    void testAddRating_outOfRangeKnowledgeableScore() throws Exception {
        mockMvc.perform(post("/ratings/add")
                        .param("name", "")
                        .param("email", "john@email.com")
                        .param("comment", "great")
                        .param("role", "professor")
                        .param("clarity", "9")
                        .param("niceness", "9")
                        .param("knowledgeableScore", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("ratings/add"));

        verify(staffRatingRepository, never()).save(any(StaffRating.class));
    }
}