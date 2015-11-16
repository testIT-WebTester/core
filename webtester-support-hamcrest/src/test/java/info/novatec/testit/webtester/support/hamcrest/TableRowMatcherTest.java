package info.novatec.testit.webtester.support.hamcrest;

import static info.novatec.testit.webtester.support.hamcrest.TableRowMatcher.hasFieldCount;
import static info.novatec.testit.webtester.support.hamcrest.TableRowMatcher.headerRow;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.TableRow;


@RunWith(MockitoJUnitRunner.class)
public class TableRowMatcherTest {

    static final int NUMBER = 3;
    static final int ANOTHER_NUMBER = 6;

    @Mock
    TableRow headerRow;
    @Mock
    TableRow row;

    @Before
    public void setUp() {
        doReturn(true).when(headerRow).isHeaderRow();
        doReturn(NUMBER).when(row).getNumberOfFields();
    }

    /* headerRow */

    @Test
    public void headerRowTrueTest() {
        assertThat(headerRow, headerRow());
    }

    @Test(expected = AssertionError.class)
    public void headerRowFalseTest() {
        assertThat(row, headerRow());
    }

    @Test
    public void notHeaderRowTrueTest() {
        assertThat(row, not(headerRow()));
    }

    @Test(expected = AssertionError.class)
    public void notHeaderRowFalseTest() {
        assertThat(headerRow, not(headerRow()));
    }

    /* hasNumberOfRows */

    @Test
    public void hasFieldCountTrueTest() {
        assertThat(row, hasFieldCount(NUMBER));
    }

    @Test(expected = AssertionError.class)
    public void hasFieldCountFalseTest() {
        assertThat(row, hasFieldCount(ANOTHER_NUMBER));
    }

    @Test
    public void notHasFieldCountTrueTest() {
        assertThat(row, not(hasFieldCount(ANOTHER_NUMBER)));
    }

    @Test(expected = AssertionError.class)
    public void notHasFieldCountFalseTest() {
        assertThat(row, not(hasFieldCount(NUMBER)));
    }

}
