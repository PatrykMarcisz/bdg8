package com.marcisz.patryk.demotesting.boardgame.service;

import com.marcisz.patryk.demotesting.boardgame.api.BoardGameClient;
import com.marcisz.patryk.demotesting.boardgame.dto.dto.BoardGameSearchingResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardGameService {

    private final BoardGameClient boardGameClient;

    @Autowired
    public BoardGameService(BoardGameClient boardGameClient) {
        this.boardGameClient = boardGameClient;
    }

    public int getCountForQuery(String query) {
        BoardGameSearchingResults search = boardGameClient.search(query);

        return Optional.ofNullable(search)
                .map(results -> results.getBoardGames().size())
                .orElse(-1);
    }

}
