package info.novatec.testit.webtester.support.hamcrest;

import info.novatec.testit.webtester.pageobjects.GenericTextField;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Contains {@link Matcher matcher} for {@link GenericTextField generic text fields}.
 *
 * @since 1.2
 */
public final class GenericTextFieldMatcher extends PageObjectMatcher {


    /**
     * returns whether the {@link GenericTextField generic text field} contains a certain text.
     *
     * @param text contains expected text
     * @return true if text on generic text field contains expected text
     * @since 1.2.
     */
    public static Matcher<GenericTextField> hasText(final String text) {
        return new BaseMatcher<GenericTextField>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("text to be '" + text + '\'');
            }

            @Override
            public void describeMismatch(Object genericTextField, Description mismatchDescription){
                mismatchDescription.appendText("was '" + (( GenericTextField ) genericTextField).getText() + '\'');
            }

            @Override
            public boolean matches(Object genericTextField){
                return ((GenericTextField) genericTextField).getText().equals(text);
            }

        };

    }

    private GenericTextFieldMatcher() {
            /*  utility constructor */
    }
}
