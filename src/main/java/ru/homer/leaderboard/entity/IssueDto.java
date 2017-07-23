package ru.homer.leaderboard.entity;

import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by vadmurzakov on 17.07.17.
 */
@Data
public class IssueDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private Long idIssue;
    @OneToOne
    @JoinColumn(name = "idUser")
    @JsonBackReference
    private UserDto user;
    private String basicProject;
    private IssueType issueType;
    private String summary;
    @JsonIgnore
    private DateTime creationDate;
    private int workTime;
    private String key;
}
