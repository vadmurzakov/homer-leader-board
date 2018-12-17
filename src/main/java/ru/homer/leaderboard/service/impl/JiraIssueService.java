package ru.homer.leaderboard.service.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homer.leaderboard.config.JiraClientConfiguration;
import ru.homer.leaderboard.config.properties.JiraProperties;
import ru.homer.leaderboard.config.properties.JqlRequest;
import ru.homer.leaderboard.entity.IssueDto;
import ru.homer.leaderboard.entity.parrentissue.ParrentDto;
import ru.homer.leaderboard.mapper.Mapper;
import ru.homer.leaderboard.service.IssueService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadmurzakov on 17.07.17.
 */
@Service
public class JiraIssueService implements IssueService {

    private final JqlRequest jqlRequest;
    private final Mapper mapper;
    private JiraRestClient jiraRestClient;
    private JiraProperties jiraProperties;
    private ObjectMapper jacksonMapper;

    @Autowired
    public JiraIssueService(JiraClientConfiguration jiraClientConfiguration, JqlRequest jqlRequest, Mapper mapper) {
        this.jqlRequest = jqlRequest;
        this.mapper = mapper;
        this.jiraRestClient = jiraClientConfiguration.jiraRestClient();
        this.jiraProperties = jiraClientConfiguration.getJiraProperties();
        this.jacksonMapper = new ObjectMapper();
    }

    @Override
    public List<IssueDto> getAllIssuesByUser(String user) {
        return getAllIssuesByUser(user, null);
    }

    @Override
    public List<IssueDto> getAllIssuesForLastMonthByUser(String user, int month) {
        return getAllIssuesByUser(user, month);
    }

    private List<IssueDto> getAllIssuesByUser(String user, Integer month) {
        List<Issue> issues = new ArrayList<>();
        SearchResult search;

        int startAt = jiraProperties.getStartAt();
        do {
            search = getIssuesByUser(user, month, startAt);
            search.getIssues().forEach(issues::add);
            startAt += jiraProperties.getMaxResults();
        } while (search.getTotal() > 0 && startAt < search.getTotal());

        List<IssueDto> issueDtos = new ArrayList<>();
        for (Issue issue : issues) {
            issueDtos.add(mapper.mapIssueDto(issue));
            issueDtos.get(issueDtos.size() - 1).setWorkTime(getTimeInWorklogByIssue(issue, user));
        }

        return issueDtos;
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

    private int getTimeInWorklogByIssue(Issue issue) {
        return getTimeInWorklogByIssue(issue, null);
    }

    private int getTimeInWorklogByIssue(Issue issue, String user) {
        int timeInMins = 0;
        List<Worklog> worklogs = new ArrayList<>();
        if (user != null) {
            issue.getWorklogs().forEach(Worklog -> {
                if (user.equals(Worklog.getAuthor().getName())) {
                    worklogs.add(Worklog);
                }
            });
        } else {
            issue.getWorklogs().forEach(worklogs::add);
        }

        for (Worklog worklog : worklogs) {
            timeInMins += worklog.getMinutesSpent();
        }

        return timeInMins;
    }

    @Override
    public IssueDto getIssueByKey(String key) {
        Issue issue = jiraRestClient.getIssueClient().getIssue(key).claim();
        IssueDto issueDto = mapper.mapIssueDto(issue);
        issueDto.setWorkTime(getTimeInWorklogByIssue(issue));
        return issueDto;
    }

    @Override
    public IssueDto getIssueById(String id) {
        Issue issue = jiraRestClient.getIssueClient().getIssue(id).claim();
        IssueDto issueDto = mapper.mapIssueDto(issue);
        issueDto.setWorkTime(getTimeInWorklogByIssue(issue));
        return issueDto;
    }

    @Override
    public List<Worklog> getWorklogsByIssue(Issue issue) {
        List<Worklog> worklogs = new ArrayList<>();
        if (issue != null) {
            issue.getWorklogs().forEach(worklogs::add);
            return worklogs;
        }
        return worklogs;
    }

    @Override
    public IssueDto getParrentIssue(String key) {
        Issue claim = jiraRestClient.getIssueClient().getIssue(key).claim();
        IssueField parent = claim.getField("parent");
        try {
            ParrentDto parrentDto = jacksonMapper.readValue(parent.getValue().toString(), ParrentDto.class);
            return getIssueByKey(parrentDto.getKey());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}