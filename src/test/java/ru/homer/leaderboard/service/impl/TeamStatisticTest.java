package ru.homer.leaderboard.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.homer.leaderboard.entity.IssueDto;
import ru.homer.leaderboard.enums.IssueType;
import ru.homer.leaderboard.service.Statistic;
import ru.homer.leaderboard.service.TimeSheet;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vadmurzakov on 22.07.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class TeamStatisticTest {

    @Autowired
    private Statistic teamStatistic;
    @Autowired
    private TimeSheet jiraTimeSheet;

    private final String USERNAME = "vmurzakov";

    @Test
    public void getStatisticsOnTeamFor6Month() throws Exception {
        List<ru.homer.leaderboard.entity.Statistic> statistics = teamStatistic.getStatisticsOnTeamFor6Month();
        assertNotNull(statistics.get(0).getAllTimeOnIssues() > 0);
    }

    @Test
    public void getStatisticsByUsername() throws Exception {
        ru.homer.leaderboard.entity.Statistic statistic = teamStatistic.getStatisticsByUser(USERNAME, 6);
        assertTrue(statistic.getUser().getUsername().equals(USERNAME));
    }

    @Test
    public void calcCountBug() throws Exception {
        List<ru.homer.leaderboard.entity.Statistic> statistics = teamStatistic.getStatisticsOnTeamFor6Month();
        assertTrue(statistics.get(0).getCountAllBugs() > 0);
    }

    @Test
    public void calcCountBugFilterByType() throws Exception {
        List<IssueDto> issues = jiraTimeSheet.getAllIssuesForLastMonthByUser(USERNAME, 1);
        Long count = teamStatistic.calcCountBugFilterByType(IssueType.SIMPLE_BUG, issues);
        assertTrue(count > 0);
    }

}