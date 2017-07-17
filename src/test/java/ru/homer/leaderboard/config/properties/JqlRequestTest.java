package ru.homer.leaderboard.config.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import static org.junit.Assert.assertFalse;

/**
 * Created by vadmurzakov on 17.07.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class JqlRequestTest {

    @Autowired
    private JqlRequest jiraClientRequest;

    @Test
    public void getUserAllIssuesQuery() throws Exception {
        String query = null;
        query = jiraClientRequest.getAllIssuesByUserQuery("vmurzakov", 6);
        assertFalse(StringUtils.isEmpty(query));
        query = jiraClientRequest.getAllIssuesByUserQuery("vmurzakov");
        assertFalse(StringUtils.isEmpty(query));
    }

}