package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.RadioButton;


/**
 * Contains {@link Matcher matchers} for {@link RadioButton radio buttons}.
 *
 * @since 0.9.7
 */
public class RadioButtonMatcher extends PageObjectMatcher {

    /**
     * Returns whether the {@link RadioButton radio button} is selected.
     *
     * @return true if the radio button is currently selected, otherwise false
     * @since 0.9.7
     */
    public static Matcher<RadioButton> selected() {
        return new TypeSafeMatcher<RadioButton>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("radio-button to be selected.");

            }

            @Override
            protected boolean matchesSafely(RadioButton radioButton) {
                return radioButton.isSelected();

            }

            @Override
            protected void describeMismatchSafely(RadioButton radioButton, Description mismatchDescription) {
                mismatchDescription.appendText("selected is '" + radioButton.isSelected() + '\'');
            }

        };
    }

    protected RadioButtonMatcher() {
        // utility constructor
    }

}
