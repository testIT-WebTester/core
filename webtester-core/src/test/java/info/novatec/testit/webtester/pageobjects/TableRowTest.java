package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.doReturn;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import info.novatec.testit.webtester.AbstractPageObjectTest;


public class TableRowTest extends AbstractPageObjectTest {

    @Mock
    TableField field1;
    @Mock
    TableField field2;

    @Spy
    List<TableField> tableFields = new LinkedList<TableField>();

    @InjectMocks
    TableRow cut;

    /* header row recognition */

    @Test
    public void testThatHeaderRowsAreCorrectlyIdentified_RowContainsHeaderFields_True() {
        addBothFields();
        doReturn(false).when(field1).isHeaderField();
        doReturn(true).when(field2).isHeaderField();
        assertThat(cut.isHeaderRow(), is(true));
    }

    @Test
    public void testThatHeaderRowsAreCorrectlyIdentified_RowDoesNotContainAnyHeaderFields_False() {
        addBothFields();
        doReturn(false).when(field1).isHeaderField();
        doReturn(false).when(field2).isHeaderField();
        assertThat(cut.isHeaderRow(), is(false));
    }

    /* number of fields */

    @Test
    public void testThatNumberOfFieldsCanBeRetrieved() {
        addBothFields();
        assertThat(cut.getNumberOfFields(), is(2));
    }

    /* fields */

    @Test
    public void testThatFieldsCanBeRetrievedAsAList() {
        addBothFields();
        assertThat(cut.getFields(), contains(field1, field2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testThatReturnedRowsListCantBeModified() {
        addBothFields();
        cut.getFields().clear();
    }

    @Test
    public void testThatAFieldCanBeRetrievedByItsIndex() {
        addBothFields();
        assertThat(cut.getField(0), is(sameInstance(field1)));
        assertThat(cut.getField(1), is(sameInstance(field2)));
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_rowTag() {
        stubWebElementTag("tr");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */

    private void addBothFields() {
        tableFields.add(field1);
        tableFields.add(field2);
    }

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }

}
