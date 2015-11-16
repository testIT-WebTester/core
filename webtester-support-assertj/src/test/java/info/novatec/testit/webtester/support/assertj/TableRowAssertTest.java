package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.TableRow;


@RunWith(MockitoJUnitRunner.class)
public class TableRowAssertTest {

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
        assertThat(headerRow).isHeaderRow();
    }

    @Test(expected = AssertionError.class)
    public void headerRowFalseTest() {
        assertThat(row).isHeaderRow();
    }

    @Test
    public void notHeaderRowTrueTest() {
        assertThat(row).isNotHeaderRow();
    }

    @Test(expected = AssertionError.class)
    public void notHeaderRowFalseTest() {
        assertThat(headerRow).isNotHeaderRow();
    }

    /* hasNumberOfRows */

    @Test
    public void hasFieldCountTrueTest() {
        assertThat(row).hasNumberOfFields(NUMBER);
    }

    @Test(expected = AssertionError.class)
    public void hasFieldCountFalseTest() {
        assertThat(row).hasNumberOfFields(ANOTHER_NUMBER);
    }

    @Test
    public void notHasFieldCountTrueTest() {
        assertThat(row).hasNotNumberOfFields(ANOTHER_NUMBER);
    }

    @Test(expected = AssertionError.class)
    public void notHasFieldCountFalseTest() {
        assertThat(row).hasNotNumberOfFields(NUMBER);
    }

}
