package com.marcisz.patryk.demotesting.boardgame;

import com.marcisz.patryk.demotesting.boardgame.api.TestingAppException;
import com.marcisz.patryk.demotesting.boardgame.service.BoardGameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = BoardGameController.class)
class BoardGameControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardGameService boardGameService;

    @Test
    void shouldReturnCountOfSearchResults() throws Exception {
        when(boardGameService.getCountForQuery(any())).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/boardgame/searchCounter?q=any")
                //.accept, .headers, .body itd.)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", equalTo(1)));
    }

    @Test
    void shouldHandleException() throws Exception {
        TestingAppException exception = new TestingAppException("błąd podczas wykonywania", HttpStatus.BAD_GATEWAY.value());
        when(boardGameService.getCountForQuery(any())).thenThrow(exception);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/boardgame/searchCounter?q=any")
                //.accept, .headers, .body itd.)
        )
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(jsonPath("$.code", equalTo(502)));
    }

    @Test
    void shouldHandleValidationException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/boardgame/searchCounter?q=ab")
                //.accept, .headers, .body itd.)
        )
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(jsonPath("$.code", equalTo(502)));
    }

}