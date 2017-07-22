package ru.homer.leaderboard.enums;

import com.atlassian.jira.rest.client.api.domain.IssueType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.homer.leaderboard.config.JiraClientConfiguration;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by vadmurzakov on 22.07.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class IssueTypeByBugsTest {

    @Autowired
    private JiraClientConfiguration jiraClientConfiguration;

    @Test
    public void checkEnumIssueTypeByBug() {
        List<Long> ids = IssueTypeByBug.getIds();
        List<IssueType> issueTypes = new ArrayList<>();
        jiraClientConfiguration.jiraRestClient().getMetadataClient().getIssueTypes().claim().forEach(IssueType -> issueTypes.add(IssueType));
        assertTrue(issueTypes.size() > 0 && ids.size() > 0);

        for (IssueType issueType : issueTypes) {
            if (ids.contains(issueType.getId())) {
                String name = issueType.getName();
                name.equals(IssueTypeByBug.getByName(name).getValue());
            }
        }
    }

    @Test
    public void getEnumsIssueTypeByPoductBug() {
        List<Long> list = IssueTypeByBug.getIdsByType(ru.homer.leaderboard.enums.IssueType.PRODUCT_BUG);
        assertTrue(list.size() == 2);
    }

}