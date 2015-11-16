package info.novatec.testit.webtester.pageobjects;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.api.enumerations.Method;


/**
 * Represents the row of a HTML table. The following HTML elements are
 * supported:
 * <ul>
 * <li><b>tag:</b> tr</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class TableRow extends PageObject {

    @IdentifyUsing(method = Method.XPATH, value = "./th | ./td")
    private List<TableField> tableFields;

    /**
     * Returns whether or not this row is part of the table header. This is done
     * by checking if any of it's fields is a header field (TH).
     *
     * @return true if this is a header row, otherwise false
     * @since 0.9.0
     */
    public boolean isHeaderRow() {
        for (TableField tablefield : tableFields) {
            if (tablefield.isHeaderField()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of {@link TableField fields} of this {@link TableRow
     * row}.
     *
     * @return the number of fields of this row
     * @since 0.9.0
     */
    public Integer getNumberOfFields() {
        return tableFields.size();
    }

    /**
     * Returns all the {@link TableField fields} of this {@link TableRow row} as
     * an unmodifiable list (collection). <br>
     *
     * @return all fields of this row
     * @since 0.9.0
     */
    public List<TableField> getFields() {
        return Collections.unmodifiableList(tableFields);
    }

    /**
     * Returns the {@link TableField field} for the given index.
     *
     * @param index the index of the field to return
     * @return the field
     * @throws IndexOutOfBoundsException if given index does not exist
     * @since 0.9.0
     */
    public TableField getField(final Integer index) {
        return executeAction(new PageObjectCallbackWithReturnValue<TableField>() {

            @Override
            public TableField execute(PageObject arg) {
                return tableFields.get(index);
            }

        });
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        return "tr".equalsIgnoreCase(webElement.getTagName());
    }

}
