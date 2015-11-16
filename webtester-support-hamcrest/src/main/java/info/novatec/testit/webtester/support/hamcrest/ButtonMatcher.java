package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.Button;


/**
 * Contains {@link Matcher matchers} for {@link Button buttons}.
 *
 * @since 0.9.7
 */
public class ButtonMatcher extends PageObjectMatcher {

    /**
     * Returns whether the {@link Button button} has a certain value.
     *
     * @param value the expected value
     * @return true if value matches, otherwise false
     * @since 0.9.7
     */
    public static Matcher<Button> hasValue(final String value) {
        return new TypeSafeMatcher<Button>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("value to be '" + value + '\'');
            }

            @Override
            protected boolean matchesSafely(Button button) {
                return button.getValue().equals(value);
            }

            @Override
            protected void describeMismatchSafely(Button button, Description mismatchDescription) {
                mismatchDescription.appendText("was '" + button.getValue() + '\'');
            }

        };

    }

    /**
     * Returns whether the {@link Button button} has a certain label.
     *
     * @param label the asserted label
     * @return true if label matches, otherwise false
     * @since 0.9.7
     */
    public static Matcher<Button> hasLabel(final String label) {
        return new TypeSafeMatcher<Button>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("label to be '" + label + '\'');
            }

            @Override
            protected boolean matchesSafely(Button button) {
                return button.getLabel().equals(label);
            }

            @Override
            protected void describeMismatchSafely(Button button, Description mismatchDescription) {
                mismatchDescription.appendText("was '" + button.getLabel() + '\'');
            }

        };

    }

    protected ButtonMatcher() {
        // utility constructor
    }

}
