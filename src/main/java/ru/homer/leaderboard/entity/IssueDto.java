package ru.homer.leaderboard.entity;

import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.api.domain.User;
import lombok.Data;
import org.joda.time.DateTime;

/**
 * Created by vadmurzakov on 17.07.17.
 */
@Data
public class IssueDto {
    private User user;
    private BasicProject basicProject;
    private IssueType issueType;
    private String summary;
    private DateTime creationDate;
    private Double workTime;
    private String key;
}
