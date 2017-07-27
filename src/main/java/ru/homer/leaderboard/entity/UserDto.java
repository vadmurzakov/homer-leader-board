package ru.homer.leaderboard.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vadmurzakov on 20.07.17.
 */
@Data
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String fullname;
    private String email;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<IssueDto> issueDtos;
    private String avatarUri;

    //статистика
    private long countIssues;           //всего задач
    private double allTimeOnIssues;     //всего потрачено минут на задачи

    private long countAllBugs;          //всего багов
    private double timeOnAllBugs;       //всего потрачено на все типы багов

    private long countProductBugs;      //кол-во продуктовых багов
    private double timeOnProductBugs;   //потрачено время на продуктовые баги
    private double hourPerProductBug;   //количество часов в среднем на 1 продуктовый баг

    private long countSimpleBugs;       //кол-во обычных багов
    private double timeOnSimpleBugs;    //потрачено время на обычные баги
    private double hourPerSimpleBug;    //количество часов в среднем на 1 баг

}
