package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.TableField;


/**
 * Contains {@link Matcher matchers} for {@link TableField table fields}.
 *
 * @since 0.9.7
 */
public class TableFieldMatcher extends PageObjectMatcher {

    /**
     * Returns whether the {@link TableField table field} is a header field.
     *
     * @return true if the table field is part of the header row, otherwise
     * false
     * @since 0.9.7
     */
    public static Matcher<TableField> headerField() {
        return new TypeSafeMatcher<TableField>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("table field to be a header field");
            }

            @Override
            protected boolean matchesSafely(TableField tablefield) {
                return tablefield.isHeaderField();
            }

            @Override
            protected void describeMismatchSafely(TableField item, Description mismatchDescription) {
                mismatchDescription.appendText("header field is '" + item.isHeaderField() + '\'');
            }

        };
    }

    protected TableFieldMatcher() {
        // utility constructor
    }

}
