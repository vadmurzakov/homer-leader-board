package ru.homer.leaderboard.entity.parrentissue;

import lombok.Data;

/**
 * Created by vadmurzakov on 17.08.17.
 */
@Data
public class FieldDto {
    private String summary;
    private StatusDto status;
    private PriorityDto priority;
    private IssueTypeDto issuetype;
}