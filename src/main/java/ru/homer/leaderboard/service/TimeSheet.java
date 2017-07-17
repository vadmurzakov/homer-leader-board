package ru.homer.leaderboard.service;

import com.atlassian.jira.rest.client.api.domain.Issue;

import java.util.List;

/**
 * Created by vadmurzakov on 17.07.17.
 */
public interface TimeSheet {

    /**
     * Получаем список задач пользователя за все время
     *
     * @param user логин пользователя
     * @return список задач
     */
    public List<Issue> getAllIssuesByUser(String user);

    /**
     * Получаем все задачи пользователя за последние n-месяцев
     *
     * @param month кол-во месяцев
     * @param user  логин пользователя
     * @return список задач
     */
    public List<Issue> getAllIssuesForLastMonthByUser(String user, int month);

}
