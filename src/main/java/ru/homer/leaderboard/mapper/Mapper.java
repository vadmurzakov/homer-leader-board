package ru.homer.leaderboard.mapper;

import com.atlassian.jira.rest.client.api.domain.Issue;
import ru.homer.leaderboard.entity.IssueDto;

/**
 * Created by vadmurzakov on 20.07.17.
 */
public interface Mapper {

    public IssueDto mapIssueDto(Issue issue);

}
