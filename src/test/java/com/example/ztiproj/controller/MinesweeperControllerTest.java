package com.example.ztiproj.controller;

import com.example.ztiproj.controller.impl.MinesweeperControllerImpl;
import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.service.impl.MinesweeperServiceImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-29
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class MinesweeperControllerTest {
    private MockMvc mvc;
    @MockBean
    private MinesweeperServiceImpl service;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new MinesweeperControllerImpl(service)).build();
    }

    @Test
    public void shouldReturnListOfResults() throws Exception {
        when(service.getRanking())
                .thenReturn(List.of(MinesweeperDto.builder().userName("matib").time(13L).build()));

        mvc.perform(MockMvcRequestBuilders.get("/minesweeper")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("matib"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].time").value(13L));
    }

    @Test
    public void shouldReturnUserListOfResults() throws Exception {
        String userName = "matib";
        when(service.getUserRanking(userName))
                .thenReturn(List.of(MinesweeperDto.builder().userName(userName).time(4L).build()));

        mvc.perform(MockMvcRequestBuilders.get("/minesweeper/{username}", userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value(userName))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].time").value(4L));
    }

    @Test
    public void shouldAddResult() throws Exception {
        Object object = new Object() {
            private final String userName = "matib";
            private final Long time = 413L;
        };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonObject = objectMapper.writeValueAsString(object);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/minesweeper")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonObject);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(service).addResult(any(MinesweeperDto.class));
    }

    @Test
    public void shouldReturn400StatusCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/minesweeper")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldAllUserResultsBeDeleted() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/minesweeper/{username}", "matib"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service).deleteAllUserResults(anyString());
    }
}
