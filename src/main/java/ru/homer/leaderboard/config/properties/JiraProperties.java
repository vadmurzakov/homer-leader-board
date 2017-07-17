package ru.homer.leaderboard.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jira")
public class JiraProperties {

    private String url;
    private String login;
    private String password;
    private int maxResults;
    private int startAt;

}
