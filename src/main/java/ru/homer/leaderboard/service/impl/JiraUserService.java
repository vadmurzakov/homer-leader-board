package ru.homer.leaderboard.service.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homer.leaderboard.config.JiraClientConfiguration;
import ru.homer.leaderboard.entity.IssueDto;
import ru.homer.leaderboard.entity.IssueStatistic;
import ru.homer.leaderboard.entity.UserDto;
import ru.homer.leaderboard.enums.IssueType;
import ru.homer.leaderboard.enums.IssueTypeByBug;
import ru.homer.leaderboard.mapper.Mapper;
import ru.homer.leaderboard.service.IssueService;
import ru.homer.leaderboard.service.UserService;

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

        issueStatistics.add(getStatisticForBug(IssueType.SIMPLE_BUG, issues));
        issueStatistics.add(getStatisticForBug(IssueType.PRODUCT_BUG, issues));

        issueStatistics.add(new IssueStatistic(
                IssueType.BUGS,
                issueStatistics.get(0).getCount() + issueStatistics.get(1).getCount(),
                issueStatistics.get(0).getTime() + issueStatistics.get(1).getTime()
        ));

        userDto.setIssueStatistics(issueStatistics);

        userDto.setIssueDtos(issues);

        return userDto;
    }

    /**
     * Получить статистику в зависимости от типа бага
     *
     * @param issueType тип бага
     * @param issueDtos список задач
     * @return IssueStatistic
     */
    private IssueStatistic getStatisticForBug(IssueType issueType, List<IssueDto> issueDtos) {
        long count = 0;
        double time = 0;
        List<Long> idsIssueType = IssueTypeByBug.getIdsByType(issueType);
        for (IssueDto issueDto : issueDtos) {
            if (idsIssueType.contains(issueDto.getIssueType().getId())) {
                count++;
                time += issueDto.getWorkTime();
            }
        }
        return new IssueStatistic(issueType, count, time);
    }

}
