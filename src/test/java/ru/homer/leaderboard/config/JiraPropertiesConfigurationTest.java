package ru.homer.leaderboard.config;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class JiraPropertiesConfigurationTest {

    @Autowired
    private JiraClientConfiguration jiraClientConfiguration;

    @Test
    public void jiraRestClientTest() throws Exception {
        JiraRestClient jiraRestClient = jiraClientConfiguration.jiraRestClient();
        assertNotNull(jiraRestClient);
    }

    @Test
    public void getAllIssueType() throws Exception {
        List<IssueType> issueTypes = new ArrayList<>();
        JiraRestClient jiraRestClient = jiraClientConfiguration.jiraRestClient();
        Iterable<IssueType> issueTypeIterable = jiraRestClient.getMetadataClient().getIssueTypes().claim();
        issueTypeIterable.forEach(IssueType -> issueTypes.add(IssueType));
        assertTrue(issueTypes.size() > 0);
    }

}