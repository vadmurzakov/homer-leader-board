package ru.homer.leaderboard.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * Created by vadmurzakov on 27.07.17.
 */
@Data
public class ProjectDto {
    @Id
    private Long id;
    private String key;
    private String name;

    public ProjectDto(Long id, String key, String name) {
        this.id = id;
        this.key = key;
        this.name = name;
    }
}
