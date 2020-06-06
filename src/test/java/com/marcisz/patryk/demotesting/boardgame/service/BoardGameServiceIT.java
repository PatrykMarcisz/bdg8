package com.marcisz.patryk.demotesting.boardgame.service;

import com.marcisz.patryk.demotesting.DemoTestingApplication;
import com.marcisz.patryk.demotesting.boardgame.api.BoardGameClient;
import com.marcisz.patryk.demotesting.boardgame.dto.dto.BoardGame;
import com.marcisz.patryk.demotesting.boardgame.dto.dto.BoardGameSearchingResults;
import com.marcisz.patryk.demotesting.boardgame.dto.dto.Name;
import com.marcisz.patryk.demotesting.boardgame.dto.dto.YearPublished;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DemoTestingApplication.class)
class BoardGameServiceIT {

    //po pierwsze testujemy metody publiczne
    //po drugie testujemy wynik tej metody,

    @Autowired
    private BoardGameService boardGameService;

    @Test
    void shouldReturnCorrectSizeOfSearchResults() {
        String query = "Avalon";

        //when
        int count = boardGameService.getCountForQuery(query);

        //then
        assertEquals(14, count);
    }

}