package info.novatec.testit.webtester.pageobjects;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.api.enumerations.Method;


/**
 * Represents a HTML table. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> table</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class Table extends PageObject {

    @IdentifyUsing(method = Method.XPATH, value = "./tr | ./*/tr")
    private List<TableRow> tableRows;

    /**
     * Returns the number of {@link TableRow rows} of this {@link Table table}.
     * <br>
     * Objects that count among this are the 'tr' tags of this table - including
     * all that are direct descendants of the 'table' tag or that are included
     * inside 'thead' and 'tbody' tags.
     *
     * @return the number of rows of this table
     * @since 0.9.0
     */
    public Integer getNumberOfRows() {
        return tableRows.size();
    }

    /**
     * Returns all the {@link TableRow rows} for this {@link Table table} as an
     * unmodifiable list (collection). <br>
     * Objects that count among this are the 'tr' tags of this table - including
     * all that are direct descendants of the 'table' tag or that are included
     * inside 'thead' and 'tbody' tags.
     *
     * @return all rows of this table
     * @since 0.9.0
     */
    public List<TableRow> getRows() {
        return Collections.unmodifiableList(tableRows);
    }

    /**
     * Returns the {@link TableRow row} for the given index.
     *
     * @param index the index of the row to return
     * @return the row
     * @throws IndexOutOfBoundsException if given index does not exist
     * @since 0.9.0
     */
    public TableRow getRow(final int index) {
        return executeAction(new PageObjectCallbackWithReturnValue<TableRow>() {

            @Override
            public TableRow execute(PageObject pageObject) {
                return tableRows.get(index);
            }

        });
    }

    /**
     * Returns the {@link TableField table field} for the given indices (row and
     * column).
     *
     * @param rowIndex the row index of the field to return
     * @param columnIndex the column index of the field to return
     * @return the field
     * @throws IndexOutOfBoundsException if given any index does not exist
     * @since 0.9.0
     */
    public TableField getField(final int rowIndex, final int columnIndex) {
        return executeAction(new PageObjectCallbackWithReturnValue<TableField>() {

            @Override
            public TableField execute(PageObject pageObject) {
                return tableRows.get(rowIndex).getField(columnIndex);
            }

        });
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        return "table".equalsIgnoreCase(webElement.getTagName());
    }
}
