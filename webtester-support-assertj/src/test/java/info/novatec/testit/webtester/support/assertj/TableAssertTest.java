package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.Table;


@RunWith(MockitoJUnitRunner.class)
public class TableAssertTest {

    static final int NUMBER = 3;
    static final int ANOTHER_NUMBER = 6;

    @Mock
    Table table;

    @Before
    public void setUp() {
        doReturn(NUMBER).when(table).getNumberOfRows();
    }

    /* hasNumberOfRows */

    @Test
    public void hasNumberOfRowsTrueTest() {
        assertThat(table).hasNumberOfRows(NUMBER);
    }

    @Test(expected = AssertionError.class)
    public void hasNumberOfRowsFalseTest() {
        assertThat(table).hasNumberOfRows(ANOTHER_NUMBER);
    }

    @Test
    public void notHasNumberOfRowsTrueTest() {
        assertThat(table).hasNotNumberOfRows(ANOTHER_NUMBER);
    }

    @Test(expected = AssertionError.class)
    public void notHasNumberOfRowsFalseTest() {
        assertThat(table).hasNotNumberOfRows(NUMBER);
    }

}
