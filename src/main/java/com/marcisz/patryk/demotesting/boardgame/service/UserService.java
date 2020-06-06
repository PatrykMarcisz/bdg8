package com.marcisz.patryk.demotesting.boardgame.service;

import com.marcisz.patryk.demotesting.boardgame.dto.User;
import com.marcisz.patryk.demotesting.boardgame.repository.UserInfoDao;
import com.marcisz.patryk.demotesting.boardgame.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Transactional
    public void createUser(User userInfo) {
        UserInfoDao  dao = new UserInfoDao();
        dao.setUserName(userInfo.getUserName());
        dao.setPassword(userInfo.getPassword());
        userInfoRepository.save(dao);
    }
}
