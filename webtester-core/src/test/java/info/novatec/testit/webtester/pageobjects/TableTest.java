package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import info.novatec.testit.webtester.AbstractPageObjectTest;


public class TableTest extends AbstractPageObjectTest {

    @Mock
    TableRow row1;
    @Mock
    TableRow row2;

    @Spy
    List<TableRow> tableRows = new LinkedList<TableRow>();

    @InjectMocks
    Table cut;

    /* number of rows */

    @Test
    public void testThatNumberOfRowsCanBeRetrieved() {
        addBothRows();
        assertThat(cut.getNumberOfRows(), is(2));
    }

    /* rows */

    @Test
    public void testThatRowsCanBeRetrievedAsAList() {
        addBothRows();
        assertThat(cut.getRows(), contains(row1, row2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testThatReturnedRowsListCantBeModified() {
        addBothRows();
        cut.getRows().clear();
    }

    @Test
    public void testThatARowCanBeRetrievedByItsIndex() {
        addBothRows();
        assertThat(cut.getRow(0), is(sameInstance(row1)));
        assertThat(cut.getRow(1), is(sameInstance(row2)));
    }

    /* fields */

    @Test
    public void testThatAFieldCanBeRetrievedByItsIndices() {

        TableField field1 = mock(TableField.class);
        TableField field2 = mock(TableField.class);
        doReturn(field1).when(row1).getField(0);
        doReturn(field2).when(row1).getField(1);
        tableRows.add(row1);

        assertThat(cut.getField(0, 0), is(sameInstance(field1)));
        assertThat(cut.getField(0, 1), is(sameInstance(field2)));

    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_tableTag() {
        stubWebElementTag("table");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */

    private void addBothRows() {
        tableRows.add(row1);
        tableRows.add(row2);
    }

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }

}
