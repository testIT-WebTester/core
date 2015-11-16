package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import info.novatec.testit.webtester.pageobjects.TextField;


/**
 * Contains {@link Matcher matchers} for {@link TextField text fields}.
 *
 * @since 0.9.7
 */
public class TextFieldMatcher extends PageObjectMatcher {

    /**
     * Returns whether the {@link TextField text field} contains a certain text.
     *
     * @param text the expected text
     * @return true if text on text field matches expectation, otherwise false
     * @since 0.9.7
     */
    public static Matcher<TextField> hasText(final String text) {
        return new BaseMatcher<TextField>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("text to be '" + text + '\'');
            }

            @Override
            public void describeMismatch(final Object textfield, final Description mismatchDescription) {
                mismatchDescription.appendText("was '" + (( TextField ) textfield).getText() + '\'');
            }

            @Override
            public boolean matches(Object textfield) {
                return (( TextField ) textfield).getText().equals(text);
            }

        };
    }

    protected TextFieldMatcher() {
        // utility constructor
    }

}
