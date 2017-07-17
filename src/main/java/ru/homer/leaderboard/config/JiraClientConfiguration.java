package ru.homer.leaderboard.config;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.homer.leaderboard.config.properties.JiraProperties;

import java.net.URI;

@Configuration
@Data
public class JiraClientConfiguration {

    private final JiraProperties jiraProperties;

    @Autowired
    public JiraClientConfiguration(JiraProperties jiraProperties) {
        this.jiraProperties = jiraProperties;
    }

    @Bean
    @SneakyThrows
    public JiraRestClient jiraRestClient() {
        final JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        URI uri = new URI(jiraProperties.getUrl());
        return factory.createWithBasicHttpAuthentication(uri, jiraProperties.getLogin(), jiraProperties.getPassword());
    }

}
