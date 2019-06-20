package ru.homer.leaderboard.enums;

import ru.homer.leaderboard.exception.IssueTypeNotFound;

/**
 * Created by vadmurzakov on 22.07.17.
 */
//todo надо переименовать, ибо это не issue, а тип
public enum Issue {
    SIMPLE_BUG("Simple bug"),
    PRODUCT_BUG("ProductBug"),
    DEV("Разработка"),
    BUGS("Баги"),
    ANALYTICS("Аналитика"),
    PATCH("Патчи"),
    AVG_SIMPLE_BUG("Average Simple Bug"),
    AVG_PRODUCT_BUG("Average Product Bug");

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
