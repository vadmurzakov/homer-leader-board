package ru.homer.leaderboard.entity.parrentissue;

import lombok.Data;

/**
 * Created by vadmurzakov on 17.08.17.
 */
@Data
public class StatusDto {
    private String self;
    private String description;
    private String iconUrl;
    private String name;
    private Long id;
    private StatusCategoryDto statusCategory;
}