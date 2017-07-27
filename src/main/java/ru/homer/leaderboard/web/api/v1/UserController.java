package ru.homer.leaderboard.web.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.homer.leaderboard.entity.UserDto;
import ru.homer.leaderboard.service.impl.JiraUserService;

/**
 * Created by vadmurzakov on 22.07.17.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final JiraUserService jiraUserService;

    @Autowired
    public UserController(JiraUserService jiraUserService) {
        this.jiraUserService = jiraUserService;
    }

    @RequestMapping(value = "/{username}/month/{countMonth}", method = RequestMethod.GET)
    public UserDto getStatisticsByUser(@PathVariable String username, @PathVariable int countMonth) {
        return jiraUserService.getStatisticByUsername(username, countMonth);
    }

}
