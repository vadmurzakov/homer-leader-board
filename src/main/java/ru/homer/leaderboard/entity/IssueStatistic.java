package ru.homer.leaderboard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.homer.leaderboard.enums.IssueType;

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

    private IssueType issueType;    //тип задачи
    private long count;             //количество
    private double time;            //затраченное время

    public IssueStatistic(IssueType issueType, long count, double time) {
        this.issueType = issueType;
        this.count = count;
        this.time = time;
    }
}
