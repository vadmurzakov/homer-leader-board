package ru.homer.leaderboard.enums;

import ru.homer.leaderboard.exception.IssueTypeNotFound;

/**
 * Created by vadmurzakov on 22.07.17.
 */
public enum IssueType {
    /**
     * Обычный баг
     */
    SIMPLE_BUG("Simple bug"),
    /**
     * Продуктовый баг
     */
    PRODUCT_BUG("ProductBug"),
    /**
     * Разработка
     */
    DEV("Разработка"),
    /**
     * TaskCR
     */
    TASK_CR("TaskCR"),
    /**
     * Прочее (согласование, запросы и т.д)
     */
    OTHER("Other");

    private String value;

    IssueType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static IssueType converterStringToEnum(String source) {
        for(IssueType issueType : IssueType.values()) {
            if (issueType.getValue().equals(source)) {
                return issueType;
            }
        }
        throw new IssueTypeNotFound("Can't find enum field with value = " + source);
    }
}
