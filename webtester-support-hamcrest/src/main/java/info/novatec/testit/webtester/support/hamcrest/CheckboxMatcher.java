package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.Checkbox;


/**
 * Contains {@link Matcher matchers} for {@link Checkbox checkboxes}.
 *
 * @since 0.9.7
 */
public class CheckboxMatcher extends PageObjectMatcher {

    /**
     * Returns whether the {@link Checkbox checkbox} is selected.
     *
     * @return true if checkbox is selected, otherwise false
     * @since 0.9.7
     */
    public static Matcher<Checkbox> selected() {
        return new TypeSafeMatcher<Checkbox>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("checkbox to be selected");
            }

            @Override
            protected boolean matchesSafely(Checkbox checkbox) {
                return checkbox.isSelected();
            }

            @Override
            protected void describeMismatchSafely(Checkbox item, Description mismatchDescription) {
                mismatchDescription.appendText("selected is '").appendText(item.isSelected().toString()).appendText("'");
            }

        };
    }

    protected CheckboxMatcher() {
        // utility constructor
    }

}
