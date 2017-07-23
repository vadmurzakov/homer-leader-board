package ru.homer.leaderboard.service.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homer.leaderboard.config.JiraClientConfiguration;
import ru.homer.leaderboard.entity.UserDto;
import ru.homer.leaderboard.mapper.Mapper;
import ru.homer.leaderboard.service.User;

/**
 * Created by vadmurzakov on 23.07.17.
 */
@Service
public class JiraUser implements User {

    private final JiraClientConfiguration jiraClientConfiguration;
    private final Mapper mapper;
    private JiraRestClient jiraRestClient;

    @Autowired
    public JiraUser(JiraClientConfiguration jiraClientConfiguration, Mapper mapper) {
        this.jiraClientConfiguration = jiraClientConfiguration;
        this.mapper = mapper;
        this.jiraRestClient = jiraClientConfiguration.jiraRestClient();
    }


    @Override
    public UserDto getUserByUsername(String username) {
        return mapper.mapUserDto(jiraRestClient.getUserClient().getUser(username).claim());
    }
}
