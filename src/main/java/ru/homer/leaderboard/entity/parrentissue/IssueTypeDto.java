package ru.homer.leaderboard.entity.parrentissue;

import lombok.Data;

/**
 * Created by vadmurzakov on 17.08.17.
 */
@Data
public class IssueTypeDto {
    private String self;
    private Long id;
    private String description;
    private String iconUrl;
    private String name;
    private boolean subtask;
}
