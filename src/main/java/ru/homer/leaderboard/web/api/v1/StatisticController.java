package ru.homer.leaderboard.web.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.homer.leaderboard.entity.Statistic;

import java.util.List;

/**
 * Created by vadmurzakov on 22.07.17.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticController {

    private final ru.homer.leaderboard.service.Statistic teamStatistic;

    @Autowired
    public StatisticController(ru.homer.leaderboard.service.Statistic teamStatistic) {
        this.teamStatistic = teamStatistic;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Statistic> getStatisticsOnTeamFor6Month() {
        return teamStatistic.getStatisticsOnTeamFor6Month();
    }

    @RequestMapping(value = "/{username}/month/{countMonth}", method = RequestMethod.GET)
    public Statistic getStatisticsByUser(@PathVariable String username, @PathVariable int counMonth) {
        return teamStatistic.getStatisticsByUser(username, counMonth);
    }

}
