package katlasik.board.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Anonymous user should see login screen")
    void getWelcome() throws Exception {

        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Witaj")))
                .andExpect(content().string(containsString("Zaloguj się")));

    }

    @WithMockUser
    @Test
    @DisplayName("Authenticated user should home screen")
    void getHome() throws Exception {

        mockMvc.perform(get("/home")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Witaj w aplikacji")))
                .andExpect(content().string(containsString("Wyloguj się")));

    }

}
