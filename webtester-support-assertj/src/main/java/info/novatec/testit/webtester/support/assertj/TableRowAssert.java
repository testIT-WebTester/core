package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.TableField;
import info.novatec.testit.webtester.pageobjects.TableRow;


/**
 * Contains assertions for {@link TableRow table rows}.
 *
 * @since 0.9.8
 */
public class TableRowAssert extends AbstractPageObjectAssert<TableRowAssert, TableRow> {

    public TableRowAssert(TableRow actual) {
        super(actual, TableRowAssert.class);
    }

    /**
     * Asserts that the {@link TableRow row} is a header row.
     *
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TableRowAssert isHeaderRow() {
        failOnActualBeingNull();
        if (!actual.isHeaderRow()) {
            failWithMessage("Expected table row to be a header row, but it wasn't.");
        }
        return this;
    }

    /**
     * Asserts that the {@link TableRow row} isn't a header row.
     *
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TableRowAssert isNotHeaderRow() {
        failOnActualBeingNull();
        if (actual.isHeaderRow()) {
            failWithMessage("Expected table row not to be a header row, but it was.");
        }
        return this;
    }

    /**
     * Asserts that the {@link TableRow row} has a certain number of
     * {@link TableField fields}.
     *
     * @param fieldCount the expected number of fields
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TableRowAssert hasNumberOfFields(int fieldCount) {
        failOnActualBeingNull();
        Integer actualFieldCount = actual.getNumberOfFields();
        if (!Objects.equals(actualFieldCount, fieldCount)) {
            failWithMessage("Expected table row's number of fields to be %s, but it was %s.", fieldCount, actualFieldCount);
        }
        return this;
    }

    /**
     * Asserts that the {@link TableRow row} doesn't have a certain number of
     * {@link TableField fields}.
     *
     * @param fieldCount not the expected number of fields
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TableRowAssert hasNotNumberOfFields(int fieldCount) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getNumberOfFields(), fieldCount)) {
            failWithMessage("Expected table row's number of fields to be %s, but it was.", fieldCount);
        }
        return this;
    }

}
