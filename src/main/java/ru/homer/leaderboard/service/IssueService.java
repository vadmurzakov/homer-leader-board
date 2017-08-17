package ru.homer.leaderboard.service;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import ru.homer.leaderboard.entity.IssueDto;

import java.util.List;

/**
 * Created by vadmurzakov on 17.07.17.
 */
public interface IssueService {

    /**
     * Получаем список задач пользователя за все время
     *
     * @param user логин пользователя
     * @return список задач
     */
    public List<IssueDto> getAllIssuesByUser(String user);

    /**
     * Получаем все задачи пользователя за последние n-месяцев
     *
     * @param month кол-во месяцев
     * @param user  логин пользователя
     * @return список задач
     */
    public List<IssueDto> getAllIssuesForLastMonthByUser(String user, int month);

    /**
     * Получить задачу по ключу
     *
     * @param key ключ, типа: SC-2666, HOMER-1234
     * @return IssueDto задача
     */
    public IssueDto getIssueByKey(String key);

    /**
     * Получить задачу по id
     *
     * @param id задачи
     * @return IssueDto задача
     */
    public IssueDto getIssueById(String id);

    /**
     * Возвращает список журнала работ текущей задачи
     *
     * @param issue задача
     * @return журнал работ
     */
    public List<Worklog> getWorklogsByIssue(Issue issue);

    /**
     * Если задача является подзадачей, возвращает родительскую задачу данной задачи
     *
     * @param key
     * @return
     */
    public IssueDto getParrentIssue(String key);

}
