package ru.homer.leaderboard.mapper;

import com.atlassian.jira.rest.client.api.domain.Issue;
import org.springframework.stereotype.Component;
import ru.homer.leaderboard.entity.IssueDto;

/**
 * Created by vadmurzakov on 20.07.17.
 */
@Component
public class IssueMapper implements Mapper {
    @Override
    public IssueDto mapIssueDto(Issue issue) {
        IssueDto issueDto = new IssueDto();
        issueDto.setIdIssue(issue.getId());
        issueDto.setBasicProject(issue.getProject().getName());
        issueDto.setSummary(issue.getSummary());
        issueDto.setIssueType(issue.getIssueType().getName());
        issueDto.setCreationDate(issue.getCreationDate());
        issueDto.setKey(issue.getKey());
        return issueDto;
    }
}
