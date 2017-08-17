package ru.homer.leaderboard.enums;

import ru.homer.leaderboard.exception.ProjectEnumNotFound;

/**
 * Created by vadmurzakov on 27.07.17.
 */
public enum ProjectEnum {
    JHOMER("JHOMER"),
    SC("SC"),
    JINTEG("JINTEG");

    private String value;

    ProjectEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProjectEnum converterStringToEnum(String source) {
        for (ProjectEnum projectEnum : ProjectEnum.values()) {
            if (projectEnum.getValue().equals(source)) {
                return projectEnum;
            }
        }
        throw new ProjectEnumNotFound("Can't find enum field with value = " + source);
    }
}
