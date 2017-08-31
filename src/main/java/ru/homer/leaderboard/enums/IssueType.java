package ru.homer.leaderboard.enums;

import ru.homer.leaderboard.exception.IssueTypeNotFound;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadmurzakov on 22.07.17.
 */
public enum IssueType {
    //ошибки
    ERROR_1(1L, "Ошибка", Issue.SIMPLE_BUG),
    PRODUCT_BUG(18L, "Product Bug", Issue.PRODUCT_BUG),
    INTEGRATION_BUG(37L, "Integration bug", Issue.SIMPLE_BUG),
    PRODUCT_INTEGRATION_BUG(51L, "ProductIntegrationBug", Issue.PRODUCT_BUG),
    BUG_FIX(72L, "BugFix", Issue.SIMPLE_BUG),
    SUB_BUG(10000L, "Sub-bug", Issue.SIMPLE_BUG),
    ERROR_2(10103L, "ОШИБКА_", Issue.SIMPLE_BUG),
    ERROR_KO(11303L, "Ошибка KO", Issue.SIMPLE_BUG),
    AC_HISTORY(10216L, "AC_Ошибка", Issue.SIMPLE_BUG),
    TASK_ERROR(12L, "TaskОшибки", Issue.SIMPLE_BUG),
    ERROR_3(17L, "Error", Issue.SIMPLE_BUG),

    //аналитика
    REQUEST(25L,"Request", Issue.ANALYTICS),
    REQUEST_JPERF(55L,"RequestJPERF", Issue.ANALYTICS),
    APPROVAL_SUB_TASK(20L,"Approval Sub-task", Issue.ANALYTICS),
    BP_BT(22L,"БП, БТ", Issue.ANALYTICS),

    //патчи
    PATCH(21L, "Patch", Issue.PATCH),
    PROD_PATCH(88L, "ProdPatch", Issue.PATCH);

    private Long id;
    private String value;
    private Issue BugType;

    IssueType(Long id, String value, Issue isProductBug) {
        this.id = id;
        this.value = value;
        this.BugType = isProductBug;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Issue getBugType() {
        return BugType;
    }

    public static List<Long> getIds() {
        List<Long> ids = new ArrayList<>();
        for (IssueType issueTypeByBugs : IssueType.values()) {
            ids.add(issueTypeByBugs.getId());
        }
        return ids;
    }

    public static List<Long> getIdsByType(Issue type) {
        List<Long> list = new ArrayList<>();
        for (IssueType issueTypeByBugs : IssueType.values()) {
            if (issueTypeByBugs.getBugType() == type) {
                list.add(issueTypeByBugs.getId());
            }
        }
        return list;
    }

    public static IssueType converterStringToEnum(String source) {
        for (IssueType issueType : IssueType.values()) {
            if (issueType.getValue().equals(source)) {
                return issueType;
            }
        }
        throw new IssueTypeNotFound("Can't find enum field with value = " + source);
    }


}
