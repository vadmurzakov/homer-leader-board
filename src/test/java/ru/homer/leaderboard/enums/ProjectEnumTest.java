package ru.homer.leaderboard.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by vadmurzakov on 27.07.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectEnumTest {
    @Test
    public void converterStringToEnum() throws Exception {
        assertTrue(ProjectEnum.JHOMER == ProjectEnum.converterStringToEnum("JHOMER"));
        assertTrue(ProjectEnum.JINTEG == ProjectEnum.converterStringToEnum("JINTEG"));
        assertTrue(ProjectEnum.SC == ProjectEnum.converterStringToEnum("SC"));
    }

}