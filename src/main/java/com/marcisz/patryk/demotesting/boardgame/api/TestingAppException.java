package com.marcisz.patryk.demotesting.boardgame.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.client.HttpStatusCodeException;

@Getter
@AllArgsConstructor
public class TestingAppException extends RuntimeException {

    private String message;
    private int code;

    public TestingAppException(HttpStatusCodeException e) {
        this.message = e.getMessage();
        this.code = e.getStatusCode().value();
    }
}
