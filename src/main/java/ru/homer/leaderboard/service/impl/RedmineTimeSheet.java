package ru.homer.leaderboard.service.impl;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import org.springframework.stereotype.Service;
import ru.homer.leaderboard.entity.IssueDto;
import ru.homer.leaderboard.service.TimeSheet;

import java.util.List;

/**
 * Created by vadmurzakov on 17.07.17.
 */
@Service
public class RedmineTimeSheet implements TimeSheet {
    @Override
    public List<IssueDto> getAllIssuesByUser(String user) {
        return null;
    }

    @Override
    public List<IssueDto> getAllIssuesForLastMonthByUser(String user, int month) {
        return null;
    }

    @Override
    public IssueDto getIssueByKey(String key) {
        return null;
    }

    @Override
    public IssueDto getIssueById(String id) {
        return null;
    }

    @Override
    public List<Worklog> getWorklogsByIssue(Issue issue) {
        return null;
    }
}
