package com.marcisz.patryk.demotesting.boardgame;

import com.marcisz.patryk.demotesting.boardgame.dto.User;
import com.marcisz.patryk.demotesting.boardgame.service.BoardGameService;
import com.marcisz.patryk.demotesting.boardgame.service.UserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/boardgame")
@Validated
public class BoardGameController {

    private BoardGameService boardGameService;
    private UserService userService;

    @Autowired
    public BoardGameController(BoardGameService service, UserService userService){
        this.boardGameService = service;
        this.userService = userService;
    }

    @GetMapping(path = "/searchCounter")
    public int searchCounter(@Valid @Length(min = 3, max = 20) @RequestParam("q") String query){
        return boardGameService.getCountForQuery(query);
    }

    @PostMapping(path = "/addUser")
    public void addUser(@RequestBody User userInfo){
        userService.createUser(userInfo);
    }

}
