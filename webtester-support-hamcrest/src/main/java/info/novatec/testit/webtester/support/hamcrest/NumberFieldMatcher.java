package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.NumberField;


/**
 * Contains {@link Matcher matchers} for {@link NumberField number fields}.
 *
 * @since 0.9.7
 */
public class NumberFieldMatcher extends TextFieldMatcher {

    /**
     * Returns whether the value of the {@link NumberField number field}}
     * matches the expectation.
     *
     * @param value the expected value
     * @return true if the actual value matches the expectation, otherwise false
     * @since 0.9.7
     */
    public static Matcher<NumberField> hasValue(final Long value) {
        return new TypeSafeMatcher<NumberField>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("value to be '" + value + '\'');
            }

            @Override
            protected boolean matchesSafely(NumberField numberField) {
                return value.equals(numberField.getValue());
            }

            @Override
            protected void describeMismatchSafely(NumberField numberField, Description mismatchDescription) {
                mismatchDescription.appendText("was '" + numberField.getValue() + '\'');
            }

        };

    }

    protected NumberFieldMatcher() {
        // utility constructor
    }

}
