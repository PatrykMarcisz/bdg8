package com.marcisz.patryk.demotesting.boardgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository <UserInfoDao, Long> {

    Optional<UserInfoDao> findFirstByUserName(String userName);

}
