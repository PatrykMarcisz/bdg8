package com.marcisz.patryk.demotesting.boardgame.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(statements = {"delete from user_info"})
@ActiveProfiles("test")
class UserInfoRepositoryIT {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    void shouldSaveUserInfoDtoToDatabase(){
        //given
        Object result = entityManager.createNativeQuery("select count(*) from user_info").getSingleResult();
        assertEquals("0", result.toString());

        UserInfoDao dao = new UserInfoDao();
        dao.setUserName("patryk");
        dao.setPassword("patryk123");

        //when
        userInfoRepository.saveAndFlush(dao);

        //then
        result = entityManager.createNativeQuery("select count(*) from user_info").getSingleResult();
        assertEquals("1", result.toString());
    }

    @Sql(statements = {"insert into user_info(id, user_name, password) values (100, 'Patryk', 'Patryk123')"})
    @Test
    void shouldGetByName(){
        //given
        String userName = "Patryk";
        Optional<UserInfoDao> user = userInfoRepository.findFirstByUserName(userName);

        assertEquals("Patryk", user.get().getUserName());
        assertEquals("Patryk123", user.get().getPassword());

        assertThat(user.get().getUserName(), equalTo("Patryk"));
    }


}