package com.marcisz.patryk.demotesting.boardgame.service;

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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardGameServiceTest {

    //po pierwsze testujemy metody publiczne
    //po drugie testujemy wynik tej metody,

    private BoardGameService boardGameService;

    @Mock
    private BoardGameClient boardGameClient;

    @BeforeEach
    void setUp() {
        boardGameService = new BoardGameService(boardGameClient);
    }

    @Test
    void shouldReturnCorrectSizeOfSearchResults() {
        //given
        BoardGameSearchingResults results = BoardGameSearchingResults.builder()
                .termOfUse("termuse")
                .boardGames(Collections.singletonList(
                        BoardGame.builder()
                                .id("12345")
                                .name(Name.builder()
                                        .primary("true")
                                        .value("Avalon")
                                        .build())
                                .yearpublished(YearPublished.builder()
                                        .value("1994")
                                        .build())
                                .build()
                ))
                .build();

        Name name = new Name("Avalon", "true");
        YearPublished year = new YearPublished("1994");
        BoardGame boardGame = new BoardGame("12345", name, year);
        List<BoardGame> boardGameList = Collections.singletonList(boardGame);
        BoardGameSearchingResults alternativeResults
                = new BoardGameSearchingResults("termuse", boardGameList);

        String query = "Avalon";
        when(boardGameClient.search("Avalon")).thenReturn(results);

        //when
        int count = boardGameService.getCountForQuery(query);

        //then
        assertEquals(1, count);
    }

    @Test
    @DisplayName("should get count for query -1 when null")
    void shouldGetCountForQueryShouldReturnNegativeWhenNullSearchResults(){
        //given
        String query = "something";
        //opcjonalnie
        //when(boardGameClient.search("something")).thenReturn(null);

        //when
        int count = boardGameService.getCountForQuery(query);

        //then
        assertEquals(-1, count);
    }

}