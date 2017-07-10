package ru.homer.leaderboard.config;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.homer.leaderboard.entity.JiraClient;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class JiraClientConfigurationTest {

    @Autowired
    private JiraClientConfiguration jiraClientConfiguration;

    @Test
    public void jiraRestClientTest() throws Exception {
        JiraRestClient jiraRestClient = jiraClientConfiguration.jiraRestClient();
        assertNotNull(jiraRestClient);
    }

}