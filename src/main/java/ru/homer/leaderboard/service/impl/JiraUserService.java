package ru.homer.leaderboard.service.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homer.leaderboard.config.JiraClientConfiguration;
import ru.homer.leaderboard.entity.IssueDto;
import ru.homer.leaderboard.entity.IssueStatistic;
import ru.homer.leaderboard.entity.UserDto;
import ru.homer.leaderboard.enums.Issue;
import ru.homer.leaderboard.enums.IssueType;
import ru.homer.leaderboard.mapper.Mapper;
import ru.homer.leaderboard.service.IssueService;
import ru.homer.leaderboard.service.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadmurzakov on 23.07.17.
 */
@Service
public class JiraUserService implements UserService {

    private final IssueService jiraIssueService;
    private final Mapper mapper;
    private JiraRestClient jiraRestClient;

    @Autowired
    public JiraUserService(JiraClientConfiguration jiraClientConfiguration, IssueService jiraIssueService, Mapper mapper) {
        this.jiraIssueService = jiraIssueService;
        this.mapper = mapper;
        this.jiraRestClient = jiraClientConfiguration.jiraRestClient();
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return mapper.mapUserDto(jiraRestClient.getUserClient().getUser(username).claim());
    }

    @Override
    public UserDto getStatisticByUsername(String username, int countMonth) {
        UserDto userDto = getUserByUsername(username);
        List<IssueDto> issues = jiraIssueService.getAllIssuesForLastMonthByUser(username, countMonth);
        List<IssueStatistic> issueStatistics = new ArrayList<>();

        issueStatistics.add(getStatisticByIssueType(Issue.SIMPLE_BUG, issues));
        issueStatistics.add(getAvgStatisticByBug(Issue.AVG_SIMPLE_BUG, issueStatistics.get(issueStatistics.size() - 1)));
        issueStatistics.add(getStatisticByIssueType(Issue.PRODUCT_BUG, issues));
        issueStatistics.add(getAvgStatisticByBug(Issue.AVG_PRODUCT_BUG, issueStatistics.get(issueStatistics.size() - 1)));

        issueStatistics.add(new IssueStatistic(
                Issue.BUGS,
                issueStatistics.get(0).getCount() + issueStatistics.get(1).getCount(),
                issueStatistics.get(0).getTime() + issueStatistics.get(1).getTime()
        ));

        issueStatistics.add(getStatisticByIssueType(Issue.ANALYTICS, issues));
        issueStatistics.add(getStatisticByIssueType(Issue.PATCH, issues));
        issueStatistics.add(getStatisticForDev(issueStatistics, issues));

        userDto.setIssueStatistics(issueStatistics);

        userDto.setIssueDtos(issues);

        return userDto;
    }

    /**
     * Получить статистику в зависимости от типа задачи
     *
     * @param issue тип бага
     * @param issueDtos список задач
     * @return IssueStatistic
     */
    private IssueStatistic getStatisticByIssueType(Issue issue, List<IssueDto> issueDtos) {
        long count = 0;
        double time = 0;
        List<Long> idsIssueType = IssueType.getIdsByType(issue);
        for (IssueDto issueDto : issueDtos) {
            if (idsIssueType.contains(issueDto.getIssueType().getId()) && issueDto.getWorkTime() > 10) {
                count++;
                time += issueDto.getWorkTime();
            }
        }
        return new IssueStatistic(issue, count, new BigDecimal(time / 60).setScale(2, RoundingMode.UP).doubleValue());
    }

    private IssueStatistic getStatisticForDev(List<IssueStatistic> issueStatistic, List<IssueDto> issueDtos) {
        long count = 0;
        double time = 0;
        for (IssueDto issueDto : issueDtos) {
            if (issueDto.getWorkTime() > 10) {
                count ++;
                time += issueDto.getWorkTime();
            }
        }

        long countOtherIssue = 0;
        double timeOtherIssue = 0;
        for (IssueStatistic issue : issueStatistic) {
            if (issue.getIssue() != Issue.BUGS) {
                countOtherIssue += issue.getCount();
                timeOtherIssue += issue.getTime();
            }
        }

        return new IssueStatistic(
                Issue.DEV,
                count - countOtherIssue,
                new BigDecimal((time - timeOtherIssue)/60).setScale(2,RoundingMode.UP).doubleValue());
    }

    private IssueStatistic getAvgStatisticByBug(Issue issue, IssueStatistic issueStatistic) {
        Double avgTime = 0d;
        if (issueStatistic.getCount() != 0) {
            avgTime = new BigDecimal((issueStatistic.getTime() / issueStatistic.getCount())).setScale(2, RoundingMode.UP).doubleValue();
        }
        return new IssueStatistic(issue, avgTime);
    }

}
