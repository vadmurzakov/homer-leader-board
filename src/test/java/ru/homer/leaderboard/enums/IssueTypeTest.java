package ru.homer.leaderboard.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by vadmurzakov on 25.07.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueTypeTest {

    @Test
    public void converterStringToEnum() throws Exception {
        IssueType issueType = IssueType.converterStringToEnum("TaskCR");
        assertTrue(issueType == IssueType.TASK_CR);
    }

}