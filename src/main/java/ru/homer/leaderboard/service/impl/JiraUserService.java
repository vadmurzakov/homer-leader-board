package ru.homer.leaderboard.service.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homer.leaderboard.config.JiraClientConfiguration;
import ru.homer.leaderboard.entity.IssueDto;
import ru.homer.leaderboard.entity.UserDto;
import ru.homer.leaderboard.enums.IssueType;
import ru.homer.leaderboard.enums.IssueTypeByBug;
import ru.homer.leaderboard.mapper.Mapper;
import ru.homer.leaderboard.service.IssueService;
import ru.homer.leaderboard.service.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by vadmurzakov on 23.07.17.
 */
@Service
public class JiraUserService implements UserService {

    private final JiraClientConfiguration jiraClientConfiguration;
    private final IssueService jiraIssueService;
    private final Mapper mapper;
    private JiraRestClient jiraRestClient;

    @Autowired
    public JiraUserService(JiraClientConfiguration jiraClientConfiguration, IssueService jiraIssueService, Mapper mapper) {
        this.jiraClientConfiguration = jiraClientConfiguration;
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

        userDto.setCountIssues(issues.size());

        userDto.setCountProductBugs(calcCountBugFilterByType(IssueType.PRODUCT_BUG, issues));
        userDto.setCountSimpleBugs(calcCountBugFilterByType(IssueType.SIMPLE_BUG, issues));
        userDto.setCountAllBugs(userDto.getCountProductBugs() + userDto.getCountSimpleBugs());

        userDto.setTimeOnProductBugs(calcTimeFilterByType(IssueType.PRODUCT_BUG, issues));
        userDto.setTimeOnSimpleBugs(calcTimeFilterByType(IssueType.SIMPLE_BUG, issues));
        userDto.setTimeOnAllBugs(userDto.getTimeOnProductBugs() + userDto.getTimeOnSimpleBugs());

        double hourPerProductBug;
        double hourPerSimpleBug;

        try {
            hourPerSimpleBug = userDto.getTimeOnSimpleBugs() / 60 / (double) userDto.getCountSimpleBugs();
            userDto.setHourPerSimpleBug(new BigDecimal(hourPerSimpleBug).setScale(1, RoundingMode.UP).doubleValue());
        } catch (NumberFormatException ex) {
            userDto.setHourPerSimpleBug(0.0);
        }

        try {
            hourPerProductBug = userDto.getTimeOnProductBugs() / 60 / (double) userDto.getCountProductBugs();
            userDto.setHourPerProductBug(new BigDecimal(hourPerProductBug).setScale(1, RoundingMode.UP).doubleValue());
        } catch (NumberFormatException ex) {
            userDto.setHourPerProductBug(0.0);
        }

        //todo тут надо посчитать все время за все задачи, пока тут только баги
        userDto.setAllTimeOnIssues(userDto.getTimeOnAllBugs());

        return userDto;
    }

    public Long calcCountBugFilterByType(IssueType issueType, List<IssueDto> issueDtos) {
        Long count = 0L;
        List<Long> idsIssueType = IssueTypeByBug.getIdsByType(issueType);
        for (IssueDto issueDto : issueDtos) {
            if (idsIssueType.contains(issueDto.getIssueType().getId())) {
                count++;
            }
        }
        return count;
    }

    public Long calcTimeFilterByType(IssueType issueType, List<IssueDto> issueDtos) {
        Long time = 0L;
        List<Long> idsIssueType = IssueTypeByBug.getIdsByType(issueType);
        for (IssueDto issueDto : issueDtos) {
            if (idsIssueType.contains(issueDto.getIssueType().getId())) {
                time += issueDto.getWorkTime();
            }
        }
        return time;
    }

}
