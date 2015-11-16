package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.Table;
import info.novatec.testit.webtester.pageobjects.TableField;
import info.novatec.testit.webtester.pageobjects.TableRow;


/**
 * These tests check aspects of the {@link Table table} page object which can
 * only be verified by using them on a live web-site with a real {@link Browser
 * browser}. Some of them might also check if we are using Selenium features
 * correctly.
 */
public class TableIntegrationTest extends AbstractWebTesterIntegrationTest {

    static final int HEADER = 0;
    static final int ROW_ONE = 1;
    static final int ROW_TWO = 2;
    static final int ROW_THREE = 3;
    static final int FOOTER = 4;

    static final int COLUMN_ONE = 0;
    static final int COLUMN_TWO = 1;
    static final int COLUMN_THREE = 2;

    TableTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(TableTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/table.html";
    }

    /* number of rows */

    /**
     * This test verifies that modern tables with the thead, tbody and tfoot
     * tags can be accessed and the number of rows is returned.
     */
    @Test
    public final void testThatNumberOfRowsIsCorrectlyReturnedIfTableIncludesHTML5Tags() {
        assertThat(page.referenceTable.getNumberOfRows(), is(5));
    }

    /**
     * This test verifies that classical tables without the thead, tbody and
     * tfoot tags can be accessed and the number of rows is returned.
     */
    @Test
    public final void testThatNumberOfRowsIsCorrectlyReturnedIfTableDoesNotIncludesHTML5Tags() {
        assertThat(page.minimalTable.getNumberOfRows(), is(5));
    }

    /**
     * This test verifies that empty tables without rows can be accessed and
     * zero is returned.
     */
    @Test
    public final void testThatNumberOfRowsIsCorrectlyReturnedIfTableDoesNotHaveAnyRows() {
        assertThat(page.emptyTable.getNumberOfRows(), is(0));
    }

    /**
     * This test verifies that tables with empty rows can be accessed and the
     * correct number of rows is returned.
     */
    @Test
    public final void testThatNumberOfRowsIsCorrectlyReturnedIfTableContainsOneEmptyRow() {
        assertThat(page.emptyRowTable.getNumberOfRows(), is(1));
    }

    /* number of fields */

    /**
     * This test verifies that rows of modern tables with the thead, tbody and
     * tfoot tags can be accessed and the number of fields is returned.
     */
    @Test
    public final void testThatNumberOfFieldsIsCorrectlyReturnedIfTableIncludesHTML5Tags() {
        assertThat(page.referenceTable.getRow(HEADER).getNumberOfFields(), is(3));
        assertThat(page.referenceTable.getRow(ROW_ONE).getNumberOfFields(), is(3));
    }

    /**
     * This test verifies that rows of classical tables without the thead, tbody
     * and tfoot tags can be accessed and the number of fields is returned.
     */
    @Test
    public final void testThatNumberOfFieldsIsCorrectlyReturnedIfTableDoesNotIncludeHTML5Tags() {
        assertThat(page.minimalTable.getRow(HEADER).getNumberOfFields(), is(3));
        assertThat(page.minimalTable.getRow(ROW_ONE).getNumberOfFields(), is(3));
    }

    /**
     * This test verifies that empty rows can be accessed and the number of
     * fields is zero..
     */
    @Test
    public final void testThatNumberOfFieldsIsCorrectlyReturnedIfRowIsEmpty() {
        assertThat(page.emptyRowTable.getRow(HEADER).getNumberOfFields(), is(0));
    }

    /* rows */

    /**
     * This test verifies that getting a row by index returns the correct row.
     */
    @Test
    public final void testThatRowsCanBeRetrievedByIndexFromTable() {
        assertThat(page.referenceTable.getRow(ROW_ONE).getVisibleText(), is("R1F1 R1F2 R1F3"));
    }

    /**
     * This test verifies that getting all row really returns all rows.
     */
    @Test
    public final void testThatAllRowsCanBeRetrievedFromTable() {

        List<TableRow> rows = page.referenceTable.getRows();

        assertThat(rows, hasSize(5));
        assertThat(rows.get(HEADER).getVisibleText(), is("Header 1 Header 2 Header 3"));
        assertThat(rows.get(ROW_ONE).getVisibleText(), is("R1F1 R1F2 R1F3"));
        assertThat(rows.get(ROW_TWO).getVisibleText(), is("R2F1 R2F2 R2F3"));
        assertThat(rows.get(ROW_THREE).getVisibleText(), is("R3F1 R3F2 R3F3"));
        assertThat(rows.get(FOOTER).getVisibleText(), is("Footer 1 Footer 2 Footer 3"));

    }

    /* fields */

    /**
     * This test verifies that fields can be retrieved from the table by giving
     * the exact coordinates (row and column indices).
     */
    @Test
    public final void testThatFielsCanBeRetrievedByRowAndColumnIndicesFromTable() {
        assertThat(page.referenceTable.getField(ROW_TWO, COLUMN_TWO).getVisibleText(), is("R2F2"));
    }

    /**
     * This test verifies that getting a field by index returns the correct
     * field.
     */
    @Test
    public final void testThatFieldsCanBeRetrievedByColumnIndicesFromRow() {
        assertThat(page.referenceTable.getRow(ROW_TWO).getField(COLUMN_TWO).getVisibleText(), is("R2F2"));
    }

    /**
     * This test verifies that getting all fields from a row really returns all
     * fields.
     */
    @Test
    public final void testThatAllFieldsCanBeRetrievedFromRow() {

        List<TableField> fields = page.referenceTable.getRow(ROW_ONE).getFields();

        assertThat(fields, hasSize(3));
        assertThat(fields.get(COLUMN_ONE).getVisibleText(), is("R1F1"));
        assertThat(fields.get(COLUMN_TWO).getVisibleText(), is("R1F2"));
        assertThat(fields.get(COLUMN_THREE).getVisibleText(), is("R1F3"));

    }

    /* header */

    /**
     * This test verifies that header rows are recognized as such.
     */
    @Test
    public final void testThatHeaderRowIsRecognicedCorrectlyForHeaderRow() {
        assertThat(page.referenceTable.getRow(HEADER).isHeaderRow(), is(true));
    }

    /**
     * This test verifies that normal rows are not recognized as header rows.
     */
    @Test
    public final void testThatHeaderRowIsRecognicedCorrectlyForNonHeaderRow() {
        assertThat(page.referenceTable.getRow(ROW_ONE).isHeaderRow(), is(false));
    }

    /* utilities */

    public static class TableTestPage extends PageObject {

        @IdentifyUsing("referenceTable")
        Table referenceTable;
        @IdentifyUsing("minimalTable")
        Table minimalTable;
        @IdentifyUsing("emptyTable")
        Table emptyTable;
        @IdentifyUsing("emptyRowTable")
        Table emptyRowTable;

    }

}
