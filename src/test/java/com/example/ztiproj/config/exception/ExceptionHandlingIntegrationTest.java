package com.example.ztiproj.config.exception;

import com.example.ztiproj.config.exception.handler.RestResponseEntityExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
@WebMvcTest({DummyErrorController.class})
@Import({RestResponseEntityExceptionHandler.class})
class ExceptionHandlingIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    void shouldReturnMinesweeperBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/error/minesweeper"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Null dto provided!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"));
    }

    @Test
    @WithMockUser
    void shouldReturnUserBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/error/user"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User with username <test> already exists!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"));
    }
}
