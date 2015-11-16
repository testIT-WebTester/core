package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.TextArea;


/**
 * Contains assertions for {@link TextArea text areas}.
 *
 * @since 0.9.8
 */
public class TextAreaAssert extends AbstractTextFieldAssert<TextAreaAssert, TextArea> {

    public TextAreaAssert(TextArea actual) {
        super(actual, TextAreaAssert.class);
    }

    /**
     * Asserts that the {@link TextArea text area} has a certain number of
     * columns.
     *
     * @param rowCount the expected number of columns
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TextAreaAssert hasNumberOfRows(int rowCount) {
        failOnActualBeingNull();
        Integer actualRowCount = actual.getRowCount();
        if (!Objects.equals(actualRowCount, rowCount)) {
            failWithMessage("Expected text area's row count to be %s, but it was %s.", rowCount, actualRowCount);
        }
        return this;
    }

    /**
     * Asserts that the {@link TextArea text area} doesn't have a certain number
     * of columns.
     *
     * @param rowCount not the expected number of columns
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TextAreaAssert hasNotNumberOfRows(int rowCount) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getRowCount(), rowCount)) {
            failWithMessage("Expected text area's row count not to be %s, but it was.", rowCount);
        }
        return this;
    }

    /**
     * Asserts that the {@link TextArea text area} has a certain number of rows.
     *
     * @param columnCount the expected number of rows
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TextAreaAssert hasNumberOfColumns(int columnCount) {
        failOnActualBeingNull();
        Integer actualColumnCOunt = actual.getColumnCount();
        if (!Objects.equals(actualColumnCOunt, columnCount)) {
            failWithMessage("Expected text area's column count to be %s, but it was %s.", columnCount, actualColumnCOunt);
        }
        return this;
    }

    /**
     * Asserts that the {@link TextArea text area} doesn't have a certain number
     * of rows.
     *
     * @param columnCount not the expected number of rows
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TextAreaAssert hasNotNumberOfColumns(int columnCount) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getColumnCount(), columnCount)) {
            failWithMessage("Expected text area's column count to be %s, but it was.", columnCount);
        }
        return this;
    }

}
