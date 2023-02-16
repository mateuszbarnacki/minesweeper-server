package com.example.ztiproj.minesweeper.controller;

import com.example.ztiproj.minesweeper.dto.MinesweeperDto;
import com.example.ztiproj.minesweeper.service.RestMinesweeperService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-29
 */
@WebMvcTest({MinesweeperController.class})
class MinesweeperControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private RestMinesweeperService service;

    @Test
    @WithMockUser
    void shouldReturnListOfResults() throws Exception {
        when(service.getRanking())
                .thenReturn(List.of(new MinesweeperDto("matib", 13L)));

        mvc.perform(MockMvcRequestBuilders.get("/minesweeper")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("matib"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].time").value(13L));
    }

    @Test
    @WithMockUser
    void shouldReturnUserListOfResults() throws Exception {
        String userName = "matib";
        when(service.getUserRanking(userName))
                .thenReturn(List.of(new MinesweeperDto(userName, 4L)));

        mvc.perform(MockMvcRequestBuilders.get("/minesweeper/{username}", userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value(userName))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].time").value(4L));
    }

    @Test
    @WithMockUser
    void shouldAddResult() throws Exception {
        Object object = new Object() {
            private final String userName = "matib";
            private final Long time = 413L;
        };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonObject = objectMapper.writeValueAsString(object);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/minesweeper")
                .with(csrf())
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonObject);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(service).addResult(any(MinesweeperDto.class));
    }

    @Test
    @WithMockUser
    void shouldReturn400StatusCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/minesweeper")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldAllUserResultsBeDeleted() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/minesweeper/{username}", "matib")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service).deleteAllUserResults(anyString());
    }
}
