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
     *
     * @return json
     */
    public List<ru.homer.leaderboard.entity.Statistic> getStatisticsOnTeamFor6Month();

    /**
     * Считаем количество задач определенного типа из списка задач
     *
     * @param issueType тип задачи, который хотим найти
     * @param issueDtos список задач
     * @return количество задач типа issueType
     */
    public Long calcCountBugFilterByType(IssueType issueType, List<IssueDto> issueDtos);

    /**
     * Считаем затраченное время на задачи определенного типа
     *
     * @param issueType тип задачи
     * @param issueDtos список задач
     * @return время в минутах
     */
    public Long calcTimeFilterByType(IssueType issueType, List<IssueDto> issueDtos);

}
