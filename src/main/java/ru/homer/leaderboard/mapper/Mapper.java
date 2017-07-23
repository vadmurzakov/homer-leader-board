package ru.homer.leaderboard.mapper;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.User;
import ru.homer.leaderboard.entity.IssueDto;
import ru.homer.leaderboard.entity.UserDto;

/**
 * Created by vadmurzakov on 20.07.17.
 */
public interface Mapper {

    public IssueDto mapIssueDto(Issue issue);

    public UserDto mapUserDto(User user);

}
