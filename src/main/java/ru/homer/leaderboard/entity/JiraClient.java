package ru.homer.leaderboard.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jira")
@Data
public class JiraClient {

    private String url;
    private String login;
    private String password;

}
