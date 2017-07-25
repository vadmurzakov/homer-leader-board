package ru.homer.leaderboard.mapper;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.User;
import org.springframework.stereotype.Component;
import ru.homer.leaderboard.entity.IssueDto;
import ru.homer.leaderboard.entity.UserDto;

/**
 * Created by vadmurzakov on 20.07.17.
 */
@Component
public class DtoMapper implements Mapper {
    @Override
    public IssueDto mapIssueDto(Issue issue) {
        IssueDto issueDto = new IssueDto();
        issueDto.setIdIssue(issue.getId());
        issueDto.setBasicProject(issue.getProject().getName());
        issueDto.setSummary(issue.getSummary());
        issueDto.setIssueType(issue.getIssueType());
        issueDto.setCreationDate(issue.getCreationDate());
        issueDto.setKey(issue.getKey());
        return issueDto;
    }

    @Override
    public UserDto mapUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getName());
        userDto.setEmail(user.getEmailAddress());
        userDto.setFullname(user.getDisplayName());
        userDto.setAvatarUri(user.getAvatarUri().toString());
        return userDto;
    }
}
