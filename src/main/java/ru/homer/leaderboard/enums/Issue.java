package ru.homer.leaderboard.enums;

import ru.homer.leaderboard.exception.IssueTypeNotFound;

/**
 * Created by vadmurzakov on 22.07.17.
 */
public enum Issue {
    SIMPLE_BUG("Simple bug"),
    PRODUCT_BUG("ProductBug"),
    DEV("Разработка"),
    BUGS("Баги"),
    ANALYTICS("Аналитика");

    private String value;

    Issue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Issue converterStringToEnum(String source) {
        for (Issue issue : Issue.values()) {
            if (issue.getValue().equals(source)) {
                return issue;
            }
        }
        throw new IssueTypeNotFound("Can't find enum field with value = " + source);
    }
}
