package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.Table;
import info.novatec.testit.webtester.pageobjects.TableRow;


/**
 * Contains {@link Matcher matchers} for {@link Table tables}.
 *
 * @since 0.9.7
 */
public class TableMatcher extends PageObjectMatcher {

    /**
     * Returns whether the {@link Table table} has a certain number of
     * {@link TableRow rows}.
     *
     * @param count the expected number of Rows
     * @return whether the table has the expected number of rows
     * @since 0.9.7
     */
    public static Matcher<Table> hasNumberOfRows(final Integer count) {
        return new TypeSafeMatcher<Table>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("number of rows to be '" + count + '\'');
            }

            @Override
            protected void describeMismatchSafely(Table table, Description mismatchDescription) {
                mismatchDescription.appendText("was '" + table.getNumberOfRows() + '\'');
            }

            @Override
            protected boolean matchesSafely(Table table) {
                return count.equals(table.getNumberOfRows());
            }
        };
    }

    protected TableMatcher() {
        // utility constructor
    }

}
