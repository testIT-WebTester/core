package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.TextArea;


/**
 * Contains {@link Matcher matchers} for the {@link TextArea text areas}.
 *
 * @since 0.9.7
 */
public class TextAreaMatcher extends TextFieldMatcher {

    /**
     * Returns whether the {@link TextArea text area} has a certain number of
     * columns.
     *
     * @param count the expected number of columns
     * @return true if expected number matches, otherwise false
     * @since 0.9.7
     */
    public static Matcher<TextArea> hasNumberOfColumns(final Integer count) {
        return new TypeSafeMatcher<TextArea>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("number of columns to be '" + count + '\'');
            }

            @Override
            protected boolean matchesSafely(TextArea textarea) {
                return textarea.getColumnCount().equals(count);
            }

            @Override
            public void describeMismatchSafely(final TextArea textarea, final Description mismatchDescription) {
                mismatchDescription.appendText("was '" + textarea.getColumnCount() + '\'');
            }
        };

    }

    /**
     * Returns whether the {@link TextArea text area} has a certain number of
     * rows.
     *
     * @param count the expected number of rows
     * @return true if expected number matches, otherwise false
     * @since 0.9.7
     */
    public static Matcher<TextArea> hasNumberOfRows(final Integer count) {
        return new TypeSafeMatcher<TextArea>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("number of rows to be '" + count + '\'');
            }

            @Override
            protected boolean matchesSafely(TextArea textarea) {
                return textarea.getRowCount().equals(count);
            }

            @Override
            public void describeMismatchSafely(final TextArea textarea, final Description mismatchDescription) {
                mismatchDescription.appendText("was '" + textarea.getRowCount() + '\'');
            }
        };

    }

    protected TextAreaMatcher() {
        // utility constructor
    }

}
