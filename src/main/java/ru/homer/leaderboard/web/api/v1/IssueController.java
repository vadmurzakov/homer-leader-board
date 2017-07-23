package ru.homer.leaderboard.web.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.homer.leaderboard.entity.IssueDto;
import ru.homer.leaderboard.service.TimeSheet;

import java.util.List;

/**
 * Created by vadmurzakov on 20.07.17.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/issue")
public class IssueController {

    private final TimeSheet jiraTimeSheet;

    @Autowired
    public IssueController(TimeSheet jiraTimeSheet) {
        this.jiraTimeSheet = jiraTimeSheet;
    }

    @RequestMapping(value = "{user}", method = RequestMethod.GET)
    public List<IssueDto> getAllIssueByUser(@PathVariable String user) {
        return jiraTimeSheet.getAllIssuesByUser(user);
    }

    @RequestMapping(value = "{user}/month/{month}", method = RequestMethod.GET)
    public List<IssueDto> getAllIssueByUser(@PathVariable String user, @PathVariable int month) {
        return jiraTimeSheet.getAllIssuesForLastMonthByUser(user, month);
    }

    @RequestMapping(value = "/key/{key}", method = RequestMethod.GET)
    public IssueDto getIssueByKey(@PathVariable String key) {
        return jiraTimeSheet.getIssueByKey(key);
    }
}
