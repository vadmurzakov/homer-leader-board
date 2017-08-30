package ru.homer.leaderboard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.homer.leaderboard.enums.Issue;

import javax.persistence.*;

/**
 * Created by vadmurzakov on 27.07.17.
 */
@Data
public class IssueStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToOne
    @JoinColumn(name = "idUser")
    @JsonBackReference
    private UserDto user;

    private Issue issue;            //тип задачи
    private long count;             //количество
    private double time;            //затраченное время

    public IssueStatistic(Issue issue, long count, double time) {
        this.issue = issue;
        this.count = count;
        this.time = time;
    }
}
