package ru.homer.leaderboard.service;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Worklog;

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

    /**
     * Получить задачу по ключу
     * @param key ключ, типа: SC-2666, HOMER-1234
     * @return IssueDto задача
     */
    public Issue getIssueByKey(String key);

    /**
     * Получить задачу по id
     * @param id задачи
     * @return IssueDto задача
     */
    public Issue getIssueById(String id);

    /**
     * Возвращает список журнала работ текущей задачи
     * @param issue задача
     * @return журнал работ
     */
    public List<Worklog> getWorklogsByIssue(Issue issue);

}
