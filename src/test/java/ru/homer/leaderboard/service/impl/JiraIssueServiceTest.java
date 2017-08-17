package ru.homer.leaderboard.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.homer.leaderboard.entity.IssueDto;
import ru.homer.leaderboard.service.IssueService;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vadmurzakov on 17.07.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class JiraIssueServiceTest {

    @Autowired
    private IssueService jiraIssueService;

    @Test
    public void getAllIssuesByUser() {
        List<IssueDto> issues = jiraIssueService.getAllIssuesByUser("vmurzakov");
        assertTrue(issues.size() > 0);
    }

    @Test
    public void getAllIssuesForLastMonthByUser() {
        List<IssueDto> issues = jiraIssueService.getAllIssuesForLastMonthByUser("vmurzakov", 6);
        assertTrue(issues.size() > 0);
    }

    @Test
    public void getIssueByKey() throws Exception {
        IssueDto issue = jiraIssueService.getIssueByKey("JHOMER-55800");
        assertNotNull(issue);
    }

    @Test
    public void getIssueById() throws Exception {
        IssueDto issue = jiraIssueService.getIssueById("708738");
        assertNotNull(issue);
    }

    @Test
    public void getTimeInWorkLogByIssue() throws Exception {
        String key = "SC-2449";
        IssueDto issue = jiraIssueService.getIssueByKey(key);
        assertTrue(issue.getKey().equals(key));
        assertTrue(issue.getWorkTime() > 0);
    }

    @Test
    public void getParrentIssue() throws Exception {
        IssueDto issue = jiraIssueService.getParrentIssue("JHOMER-55800");
        assertNotNull(issue);
    }
}