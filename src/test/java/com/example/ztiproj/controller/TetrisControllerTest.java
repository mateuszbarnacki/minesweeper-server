package com.example.ztiproj.controller;

import com.example.ztiproj.controller.impl.TetrisControllerImpl;
import com.example.ztiproj.dto.TetrisDto;
import com.example.ztiproj.service.impl.TetrisServiceImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * @since 2022-09-08
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class TetrisControllerTest {
    private MockMvc mvc;
    @MockBean
    private TetrisServiceImpl service;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new TetrisControllerImpl(service)).build();
    }

    @Test
    public void shouldReturnListOfResults() throws Exception {
        when(service.getRanking())
                .thenReturn(List.of(TetrisDto.builder().userName("matib").score(123L).build()));

        mvc.perform(MockMvcRequestBuilders.get("/tetris")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("matib"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].score").value(123L));
    }

    @Test
    public void shouldReturnUserListOfResults() throws Exception {
        String userName = "matib";
        when(service.getUserRanking(userName))
                .thenReturn(List.of(TetrisDto.builder().userName(userName).score(13L).build()));

        mvc.perform(MockMvcRequestBuilders.get("/tetris/{username}", userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value(userName))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].score").value(13L));
    }

    @Test
    public void shouldAddResult() throws Exception {
        Object object = new Object() {
            private final String userName = "matib";
            private final Long score = 13L;
        };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonObject = objectMapper.writeValueAsString(object);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tetris")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonObject);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(service).addResult(any(TetrisDto.class));
    }

    @Test
    public void shouldReturn400StatusCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/tetris")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldAllUserResultsBeDeleted() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/tetris/{username}", "matib"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service).deleteAllUserResults(anyString());
    }
}
