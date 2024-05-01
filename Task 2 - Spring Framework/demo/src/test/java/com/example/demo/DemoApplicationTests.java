package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilders;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateBook() throws Exception {
        String bookJson = "{\"title\":\"Test Book\",\"author\":\"Test Author\",\"isbn\":\"1234567890\",\"genre\":\"Test Genre\",\"publicationYear\":2022}";
        
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Book"));
    }

    @Test
    void testGetAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void testGetBookById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateBook() throws Exception {
        String updatedBookJson = "{\"title\":\"Updated Test Book\",\"author\":\"Updated Test Author\",\"isbn\":\"0987654321\",\"genre\":\"Updated Test Genre\",\"publicationYear\":2023}";
        
        mockMvc.perform(MockMvcRequestBuilders.put("/books/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedBookJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Test Book"));
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
