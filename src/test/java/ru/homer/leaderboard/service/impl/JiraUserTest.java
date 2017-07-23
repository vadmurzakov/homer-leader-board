package ru.homer.leaderboard.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.homer.leaderboard.entity.UserDto;
import ru.homer.leaderboard.service.User;

import static org.junit.Assert.*;

/**
 * Created by vadmurzakov on 23.07.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class JiraUserTest {

    @Autowired
    private User jiraUser;

    private final String USERNAME = "vmurzakov";

    @Test
    public void getUserByUsername() throws Exception {
        UserDto user = jiraUser.getUserByUsername(USERNAME);
        assertTrue(user.getUsername().equals(USERNAME));
    }

}