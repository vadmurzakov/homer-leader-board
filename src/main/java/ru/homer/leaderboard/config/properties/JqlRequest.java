package ru.homer.leaderboard.config.properties;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by vadmurzakov on 17.07.17.
 */
@Component
public class JqlRequest {

    private Properties jql = new Properties();

    @Getter
    private Set<String> fields = new HashSet<>(
            Arrays.asList(
                    "id",
                    "key",
                    "status",
                    "project",
                    "summary",
                    "issuetype",
                    "created",
                    "updated",
                    "worklogs"
            )
    );

    public JqlRequest() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/jql.properties");
            this.jql.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAllIssuesByUserQuery(String user, Integer month) {
        if (month != null) {
            String query = jql.getProperty("jql.user.getAllIssuesForLastMonth");
            return MessageFormat.format(query, month, user);
        } else {
            return getAllIssuesByUserQuery(user);
        }
    }

    public String getAllIssuesByUserQuery(String user) {
        String query = jql.getProperty("jql.user.getAllIssues");
        return MessageFormat.format(query, user);
    }

}
