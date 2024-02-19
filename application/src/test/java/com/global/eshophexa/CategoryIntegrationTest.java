package com.global.eshophexa;

import com.global.eshophexa.ports.incoming.CategoryUseCases;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = EshopHexaApplication.class)
class CategoryIntegrationTest {

    @Autowired
    private CategoryUseCases categoryUseCases;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = "category/init-database-category.sql")
    void findAll() throws Exception {
        this.mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));

        Assertions.assertFalse(categoryUseCases.findAll().isEmpty());
    }
}
