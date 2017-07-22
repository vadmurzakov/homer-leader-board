package ru.homer.leaderboard.service;

import ru.homer.leaderboard.entity.IssueDto;
import ru.homer.leaderboard.enums.IssueType;

import java.util.List;

/**
 * Created by vadmurzakov on 22.07.17.
 */
public interface Statistic {

    /**
     * Возвращает статистику за пол года по ЦК Гомер
     * @return json
     */
    public List<ru.homer.leaderboard.entity.Statistic> getStatisticsOnTeamFor6Month();

    /**
     * Получаем количество задач определенного типа из всех задач
     * @param issueType тип задачи, который хотим найти
     * @param issueDtos список всех задач
     * @return число
     */
    public Long calcCountBugFilterByType(IssueType issueType, List<IssueDto> issueDtos);

    /**
     * Считаем затраченное время на задачи определенного типа
     * @param issueType тип задачи
     * @param issueDtos список задач
     * @return время в минутах
     */
    public Long calcTimeFilterByType(IssueType issueType, List<IssueDto> issueDtos);

}
