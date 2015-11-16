package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.Table;
import info.novatec.testit.webtester.pageobjects.TableRow;


/**
 * Contains assertions for {@link Table tables}.
 *
 * @since 0.9.8
 */
public class TableAssert extends AbstractPageObjectAssert<TableAssert, Table> {

    public TableAssert(Table actual) {
        super(actual, TableAssert.class);
    }

    /**
     * Asserts that the {@link Table table} has a certain number of
     * {@link TableRow rows}.
     *
     * @param rowCount the expected number of Rows
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TableAssert hasNumberOfRows(int rowCount) {
        failOnActualBeingNull();
        Integer actualRowCount = actual.getNumberOfRows();
        if (!Objects.equals(actualRowCount, rowCount)) {
            failWithMessage("Expected tables's row count to be %s, but it was %s.", rowCount, actualRowCount);
        }
        return this;
    }

    /**
     * Asserts that the {@link Table table} doesn't have a certain number of
     * {@link TableRow rows}.
     *
     * @param rowCount not the expected number of Rows
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TableAssert hasNotNumberOfRows(int rowCount) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getNumberOfRows(), rowCount)) {
            failWithMessage("Expected tables's row count not to be %s, but it was.", rowCount);
        }
        return this;
    }

}
