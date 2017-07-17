package ru.homer.leaderboard.service.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homer.leaderboard.config.JiraClientConfiguration;
import ru.homer.leaderboard.config.properties.JiraProperties;
import ru.homer.leaderboard.config.properties.JqlRequest;
import ru.homer.leaderboard.service.TimeSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadmurzakov on 17.07.17.
 */
@Service
public class JiraTimeSheet implements TimeSheet {

    private final JiraClientConfiguration jiraClientConfiguration;
    private final JqlRequest jqlRequest;
    private JiraRestClient jiraRestClient;
    private JiraProperties jiraProperties;

    @Autowired
    public JiraTimeSheet(JiraClientConfiguration jiraClientConfiguration, JqlRequest jqlRequest) {
        this.jiraClientConfiguration = jiraClientConfiguration;
        this.jqlRequest = jqlRequest;
        this.jiraRestClient = jiraClientConfiguration.jiraRestClient();
        this.jiraProperties = jiraClientConfiguration.getJiraProperties();
    }

    @Override
    public List<Issue> getAllIssuesByUser(String user) {
        return getAllIssuesByUser(user, null);
    }

    @Override
    public List<Issue> getAllIssuesForLastMonthByUser(String user, int month) {
        return getAllIssuesByUser(user, month);
    }

    private List<Issue> getAllIssuesByUser(String user, Integer month) {
        List<Issue> issues = new ArrayList<>();
        SearchResult search;
        int startAt = jiraProperties.getStartAt();
        do {
            search = getIssuesByUser(user, month, startAt);
            search.getIssues().forEach(Issue -> issues.add(Issue));
            startAt += jiraProperties.getMaxResults();
        } while (search.getTotal() > 0 && startAt < search.getTotal());
        return issues;
    }

    /**
     * Возвращает результат поиска по задачам конкретного пользователя
     *
     * @param user    логин пользователя в jira
     * @param month   если null, возвращает задачи за все время, иначе за последние month-месяцев
     * @param startAt позиция поиска, т.к. задачи загружаются по 50шт за раз
     * @return
     */
    private SearchResult getIssuesByUser(String user, Integer month, int startAt) {
        return jiraRestClient.getSearchClient().searchJql(
                jqlRequest.getAllIssuesByUserQuery(user, month),
                jiraProperties.getMaxResults(),
                startAt,
                jqlRequest.getFields()
        ).claim();
    }

    @Override
    public Issue getIssueByKey(String key) {
        return jiraRestClient.getIssueClient().getIssue(key).claim();
    }

    @Override
    public Issue getIssueById(String id) {
        return jiraRestClient.getIssueClient().getIssue(id).claim();
    }

    @Override
    public List<Worklog> getWorklogsByIssue(Issue issue) {
        List<Worklog> worklogs = new ArrayList<>();
        if (issue != null) {
            issue.getWorklogs().forEach(Worklog -> worklogs.add(Worklog));
            return worklogs;
        }
        return worklogs;
    }
}
