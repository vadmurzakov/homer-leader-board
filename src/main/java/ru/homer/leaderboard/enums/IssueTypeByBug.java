package ru.homer.leaderboard.enums;

import ru.homer.leaderboard.exception.IssueTypeNotFound;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadmurzakov on 22.07.17.
 */
public enum IssueTypeByBug {
    ERROR_1(1L, "Ошибка", IssueType.SIMPLE_BUG),
    PRODUCT_BUG(18L, "Product Bug", IssueType.PRODUCT_BUG),
    INTEGRATION_BUG(37L, "Integration bug", IssueType.SIMPLE_BUG),
    PRODUCT_INTEGRATION_BUG(51L, "ProductIntegrationBug", IssueType.PRODUCT_BUG),
    BUG_FIX(72L, "BugFix", IssueType.SIMPLE_BUG),
    SUB_BUG(10000L, "Sub-bug", IssueType.SIMPLE_BUG),
    ERROR_2(10103L, "ОШИБКА_", IssueType.SIMPLE_BUG),
    ERROR_KO(11303L, "Ошибка KO", IssueType.SIMPLE_BUG),
    AC_HISTORY(10216L, "AC_Ошибка", IssueType.SIMPLE_BUG),
    TASK_ERROR(12L, "TaskОшибки", IssueType.SIMPLE_BUG),
    ERROR_3(17L, "Error", IssueType.SIMPLE_BUG);

    private Long id;
    private String value;
    private IssueType getBugType;

    IssueTypeByBug(Long id, String value, IssueType isProductBug) {
        this.id = id;
        this.value = value;
        this.getBugType = isProductBug;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public IssueType getBugType() {
        return getBugType;
    }

    public static List<Long> getIds() {
        List<Long> ids = new ArrayList<>();
        for (IssueTypeByBug issueTypeByBugs : IssueTypeByBug.values()) {
            ids.add(issueTypeByBugs.getId());
        }
        return ids;
    }

    public static List<Long> getIdsByType(IssueType type) {
        List<Long> list = new ArrayList<>();
        for (IssueTypeByBug issueTypeByBugs : IssueTypeByBug.values()) {
            if (issueTypeByBugs.getBugType == type) {
                list.add(issueTypeByBugs.getId());
            }
        }
        return list;
    }

    public static IssueTypeByBug converterStringToEnum(String source) {
        for (IssueTypeByBug issueTypeByBug : IssueTypeByBug.values()) {
            if (issueTypeByBug.getValue().equals(source)) {
                return issueTypeByBug;
            }
        }
        throw new IssueTypeNotFound("Can't find enum field with value = " + source);
    }


}
