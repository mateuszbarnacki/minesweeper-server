package com.example.ztiproj.controller;

import com.example.ztiproj.controller.impl.SnakeControllerImpl;
import com.example.ztiproj.dto.SnakeDto;
import com.example.ztiproj.service.impl.SnakeServiceImpl;
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
 * @since 2022-09-08
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class SnakeControllerTest {
    private MockMvc mvc;
    @MockBean
    private SnakeServiceImpl service;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new SnakeControllerImpl(service)).build();
    }

    @Test
    public void shouldReturnListOfResults() throws Exception {
        when(service.getRanking())
                .thenReturn(List.of(SnakeDto.builder().userName("matib").score(11L).build()));

        mvc.perform(MockMvcRequestBuilders.get("/snake")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("matib"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].score").value(11L));
    }

    @Test
    public void shouldReturnUserListOfResults() throws Exception {
        String userName = "matib";
        when(service.getUserRanking(userName))
                .thenReturn(List.of(SnakeDto.builder().userName(userName).score(4L).build()));

        mvc.perform(MockMvcRequestBuilders.get("/snake/{username}", userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value(userName))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].score").value(4L));
    }

    @Test
    public void shouldAddResult() throws Exception {
        Object object = new Object() {
            private final String userName = "matib";
            private final Long score = 134L;
        };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonObject = objectMapper.writeValueAsString(object);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/snake")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonObject);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(service).addResult(any(SnakeDto.class));
    }

    @Test
    public void shouldReturn400StatusCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/snake")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldAllUserResultsBeDeleted() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/snake/{username}", "matib"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service).deleteAllUserResults(anyString());
    }
}
