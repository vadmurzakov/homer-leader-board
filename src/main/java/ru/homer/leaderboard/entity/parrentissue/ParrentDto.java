package ru.homer.leaderboard.entity.parrentissue;

import lombok.Data;

/**
 * Created by vadmurzakov on 17.08.17.
 */
@Data
public class ParrentDto {
    private String id;
    private String key;
    private String self;
    private FieldDto fields;
}
