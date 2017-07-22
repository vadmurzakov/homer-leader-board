package ru.homer.leaderboard.entity;

import lombok.Data;

/**
 * Created by vadmurzakov on 22.07.17.
 */
@Data
public class Statistic {
    private String username;
    private int countIssues;            //всего задач
    private Long allTimeOnIssues;       //всего потрачено минут на задачи

    private Long countAllBugs;          //всего багов
    private Long timeOnAllBugs;         //всего потрачено на все типы багов

    private Long countProductBugs;      //кол-во продуктовых багов
    private Long timeOnProductBugs;     //потрачено время на продуктовые баги
    private Double hourPerProductBug;   //количество часов в среднем на 1 продуктовый баг

    private Long countSimpleBugs;       //кол-во обычных багов
    private Long timeOnSimpleBugs;      //потрачено время на обычные баги
    private Double hourPerSimpleBug;    //количество часов в среднем на 1 баг
}
