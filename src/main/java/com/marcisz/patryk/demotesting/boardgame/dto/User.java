package com.marcisz.patryk.demotesting.boardgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String userName;
    private String password;
}
