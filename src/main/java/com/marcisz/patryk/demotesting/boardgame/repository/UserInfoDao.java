package com.marcisz.patryk.demotesting.boardgame.repository;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_info")
public class UserInfoDao {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;


}
