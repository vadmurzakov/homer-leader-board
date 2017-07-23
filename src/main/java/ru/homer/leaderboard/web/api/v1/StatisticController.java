package ru.homer.leaderboard.web.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.homer.leaderboard.entity.Statistic;

import java.util.List;

/**
 * Created by vadmurzakov on 22.07.17.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticController {

    @Autowired
    private ru.homer.leaderboard.service.Statistic teamStatistic;

    @RequestMapping(method = RequestMethod.GET)
    private List<Statistic> getStatisticsOnTeamFor6Month() {
        return teamStatistic.getStatisticsOnTeamFor6Month();
    }

}
