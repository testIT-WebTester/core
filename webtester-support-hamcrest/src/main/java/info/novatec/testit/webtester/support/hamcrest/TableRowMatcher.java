package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.TableField;
import info.novatec.testit.webtester.pageobjects.TableRow;


/**
 * Contains {@link Matcher matchers} for {@link TableRow table rows}.
 *
 * @since 0.9.7
 */
public class TableRowMatcher extends PageObjectMatcher {

    /**
     * Returns whether the {@link TableRow row} contains header
     * {@link TableField fields}.
     *
     * @return true if the row contains header fields, false otherwise
     */
    public static Matcher<TableRow> headerRow() {
        return new TypeSafeMatcher<TableRow>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("table row to be a header row");

            }

            @Override
            protected boolean matchesSafely(TableRow tablerow) {
                return tablerow.isHeaderRow();
            }

            @Override
            protected void describeMismatchSafely(TableRow tablerow, Description mismatchDescription) {
                mismatchDescription.appendText("header row is '" + tablerow.isHeaderRow() + '\'');
            }
        };
    }

    /**
     * Returns whether the {@link TableRow row} has a certain number of
     * {@link TableField fields}.
     *
     * @param fieldcount the expected number of fields
     * @return true if the row has the expected number of fields, false
     * otherwise
     * @since 0.9.7
     */
    public static Matcher<TableRow> hasFieldCount(final Integer fieldcount) {
        return new TypeSafeMatcher<TableRow>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("number of fields to be '" + fieldcount + '\'');
            }

            @Override
            protected boolean matchesSafely(TableRow tablerow) {
                return fieldcount.equals(tablerow.getNumberOfFields());
            }

            @Override
            protected void describeMismatchSafely(TableRow tablerow, Description mismatchDescription) {
                mismatchDescription.appendText("was '" + tablerow.getNumberOfFields() + '\'');
            }
        };
    }

    protected TableRowMatcher() {
        // utility constructor
    }

}
