package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.pageobjects.TableField;


/**
 * Contains assertions for {@link TableField table fields}.
 *
 * @since 0.9.8
 */
public class TableFieldAssert extends AbstractPageObjectAssert<TableFieldAssert, TableField> {

    public TableFieldAssert(TableField actual) {
        super(actual, TableFieldAssert.class);
    }

    /**
     * Asserts that the {@link TableField table field} is a header field.
     *
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TableFieldAssert isHeaderField() {
        failOnActualBeingNull();
        if (!actual.isHeaderField()) {
            failWithMessage("Expected table field to be part of the header, but it wasn't.");
        }
        return this;
    }

    /**
     * Asserts that the {@link TableField table field} isn't a header field.
     *
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public TableFieldAssert isNotHeaderField() {
        failOnActualBeingNull();
        if (actual.isHeaderField()) {
            failWithMessage("Expected table field not to be part of the header, but it was");
        }
        return this;
    }

}
