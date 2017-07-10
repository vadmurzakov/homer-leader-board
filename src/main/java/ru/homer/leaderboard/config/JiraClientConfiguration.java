package ru.homer.leaderboard.config;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.homer.leaderboard.entity.JiraClient;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class JiraClientConfiguration {

    @Autowired
    private JiraClient jiraClient;

    @Bean
    public JiraRestClient jiraRestClient() throws URISyntaxException {
        final JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        URI uri = new URI(jiraClient.getUrl());
        return factory.createWithBasicHttpAuthentication(uri, jiraClient.getLogin(), jiraClient.getPassword());
    }

}
