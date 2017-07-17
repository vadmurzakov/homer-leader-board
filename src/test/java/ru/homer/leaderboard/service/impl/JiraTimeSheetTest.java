package ru.homer.leaderboard.service.impl;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.homer.leaderboard.service.TimeSheet;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vadmurzakov on 17.07.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class JiraTimeSheetTest {

    @Autowired
    private TimeSheet jiraTimeSheet;

    @Test
    public void getAllIssuesByUser() {
        List<Issue> issues = jiraTimeSheet.getAllIssuesByUser("vmurzakov");
        assertTrue(issues.size() > 0);
    }

    @Test
    public void getAllIssuesForLastMonthByUser() {
        List<Issue> issues = jiraTimeSheet.getAllIssuesForLastMonthByUser("vmurzakov", 6);
        assertTrue(issues.size() > 0);
    }

    @Test
    public void getIssueByKey() throws Exception {
        Issue issue = jiraTimeSheet.getIssueByKey("SC-2666");
        assertNotNull(issue);
    }

    @Test
    public void getIssueById() throws Exception {
        Issue issue = jiraTimeSheet.getIssueById("708738");
        assertNotNull(issue);
    }

    @Test
    public void getWorklogByIssue() throws Exception {
        Issue issue = jiraTimeSheet.getIssueByKey("SC-2666");
        List<Worklog> worklogs = jiraTimeSheet.getWorklogsByIssue(issue);
        assertTrue(worklogs.size() > 0);
    }
}