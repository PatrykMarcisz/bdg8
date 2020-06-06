package com.marcisz.patryk.demotesting.boardgame.api;

import com.marcisz.patryk.demotesting.boardgame.dto.dto.BoardGameSearchingResults;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;

@RestClientTest(BoardGameClient.class)
class BoardGameClientIT {

    @Autowired
    private BoardGameClient boardGameClient;

    @Autowired
    private MockRestServiceServer mockServer;

    Resource searchResultResponse = new ClassPathResource("/mock/boargame-client-search-results.xml");

    @Test
    void shouldReturnCorrectResponse(){
        //given
        mockServer.expect(requestTo("https://www.boardgamegeek.com/xmlapi/search?search=Avalon"))
                //accept, //content-type, //X-Custom
                .andRespond(MockRestResponseCreators.withSuccess(searchResultResponse, MediaType.APPLICATION_XML));
        String query = "Avalon";

        //when
        BoardGameSearchingResults searchResults = boardGameClient.search(query);

        //then
        assertEquals(3, searchResults.getBoardGames().size());
        assertEquals("https://boardgamegeek.com/xmlapi/termsofuse", searchResults.getTermOfUse());
        assertEquals("1862", searchResults.getBoardGames().get(0).getId());
    }

    @Test
    void shouldThrowTestingAppExceptionWhen500(){

        //given
        mockServer.expect(requestTo("https://www.boardgamegeek.com/xmlapi/search?search=Avalon"))
                //accept, //content-type, //X-Custom
                .andRespond(MockRestResponseCreators.withServerError());
        String query = "Avalon";

        //when //then
        TestingAppException testingAppException = assertThrows(TestingAppException.class,
                () -> boardGameClient.search(query)
        );

        assertEquals("500 Internal Server Error: [no body]", testingAppException.getMessage());
        assertEquals(500, testingAppException.getCode());
    }

    @TestFactory
    Stream<DynamicTest> shouldErrorsFrom400To500MapToTestingAppException(){
        return Stream.of(HttpStatus.CONFLICT, HttpStatus.FORBIDDEN, HttpStatus.BAD_REQUEST, HttpStatus.BAD_GATEWAY)
                .map(status -> DynamicTest.dynamicTest(
                        "should " + status.toString() + " map to TestingAppException",
                        () -> {
                            mockServer.expect(requestTo("https://www.boardgamegeek.com/xmlapi/search?search=Avalon"))
                                    .andRespond(MockRestResponseCreators.withStatus(status));
                            String query = "Avalon";
                            assertThrows(TestingAppException.class,
                                    () -> boardGameClient.search(query)
                            );
                            mockServer.reset();
                        }
                ));
    }



}