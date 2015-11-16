package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.TextArea;


@RunWith(MockitoJUnitRunner.class)
public class TextAreaAssertTest {

    static final int ROW_COUNT = 4;
    static final int ANOTHER_ROW_COUNT = 7;
    static final int COLUMN_COUNT = 6;
    static final int ANOTHER_COLUMN_COUNT = 3;

    @Mock
    TextArea textArea;

    @Before
    public void setUp() {
        doReturn(ROW_COUNT).when(textArea).getRowCount();
        doReturn(COLUMN_COUNT).when(textArea).getColumnCount();
    }

    /* hasNumberOfRows */

    @Test
    public void hasNumberOfRowsTrueTest() {
        assertThat(textArea).hasNumberOfRows(ROW_COUNT);
    }

    @Test(expected = AssertionError.class)
    public void hasNumberOfRowsFalseTest() {
        assertThat(textArea).hasNumberOfRows(ANOTHER_ROW_COUNT);
    }

    @Test
    public void notHasNumberOfRowsTrueTest() {
        assertThat(textArea).hasNotNumberOfRows(ANOTHER_ROW_COUNT);
    }

    @Test(expected = AssertionError.class)
    public void notHasNumberOfRowsFalseTest() {
        assertThat(textArea).hasNotNumberOfRows(ROW_COUNT);
    }

    /* hasNumberOfColumns */

    @Test
    public void hasNumberOfColumnsTrueTest() {
        assertThat(textArea).hasNumberOfColumns(COLUMN_COUNT);
    }

    @Test(expected = AssertionError.class)
    public void hasNumberOfColumnsFalseTest() {
        assertThat(textArea).hasNumberOfColumns(ANOTHER_COLUMN_COUNT);
    }

    @Test
    public void notHasNumberOfColumnsTrueTest() {
        assertThat(textArea).hasNotNumberOfColumns(ANOTHER_COLUMN_COUNT);
    }

    @Test(expected = AssertionError.class)
    public void notHasNumberOfColumnsFalseTest() {
        assertThat(textArea).hasNotNumberOfColumns(COLUMN_COUNT);
    }

}
