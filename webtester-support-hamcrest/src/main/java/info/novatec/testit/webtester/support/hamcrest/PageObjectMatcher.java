package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Contains {@link Matcher matchers} common to all {@link PageObject page
 * objects}.
 *
 * @since 0.9.7
 */
public class PageObjectMatcher {

    /**
     * Returns whether the {@link PageObject page object} has a certain
     * attribute with a specific value.
     *
     * @param attributeName the attribute's name
     * @param value the expected value of the attribute - it's toString() method
     * is used to compare it to the actual attribute value!
     * @return true if the text matches the text of the page object, false
     * otherwise
     * @since 0.9.9
     */
    public static Matcher<PageObject> hasAttributeWithValue(final String attributeName, final Object value) {

        return new TypeSafeMatcher<PageObject>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("attribute '" + attributeName + "' to be '" + value + '\'');
            }

            @Override
            protected void describeMismatchSafely(PageObject pageObject, Description mismatchDescription) {
                mismatchDescription.appendText("was '" + pageObject.getAttribute(attributeName) + '\'');
            }

            @Override
            protected boolean matchesSafely(PageObject pageObject) {
                return String.valueOf(value).equals(pageObject.getAttribute(attributeName));
            }

        };
    }

    /**
     * Returns whether the {@link PageObject page object} has a certain visible
     * text.
     *
     * @param text the expected text
     * @return true if the text matches the text of the page object, false
     * otherwise
     * @since 0.9.7
     */
    public static Matcher<PageObject> hasVisibleText(final String text) {

        return new TypeSafeMatcher<PageObject>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("text to be '" + text + '\'');
            }

            @Override
            protected void describeMismatchSafely(PageObject pageObject, Description mismatchDescription) {
                mismatchDescription.appendText("was '" + pageObject.getVisibleText() + '\'');
            }

            @Override
            protected boolean matchesSafely(PageObject pageObject) {
                return text.equals(pageObject.getVisibleText());
            }

        };
    }

    /**
     * Returns whether a {@link PageObject page object} is visible.
     *
     * @return true if visible, otherwise false
     * @since 0.9.7
     */
    public static Matcher<PageObject> visible() {

        return new TypeSafeMatcher<PageObject>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("object to be visible");
            }

            @Override
            protected void describeMismatchSafely(PageObject pageObject, Description mismatchDescription) {
                mismatchDescription.appendText("visible was '" + pageObject.isVisible() + '\'');
            }

            @Override
            protected boolean matchesSafely(PageObject pageObject) {
                return pageObject.isVisible();
            }

        };
    }

    /**
     * Returns whether a {@link PageObject page object} is enabled.
     *
     * @return true if enabled, otherwise false
     * @since 0.9.7
     */
    public static Matcher<PageObject> enabled() {

        return new TypeSafeMatcher<PageObject>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("object to be enabled");
            }

            @Override
            protected void describeMismatchSafely(PageObject pageObject, Description mismatchDescription) {
                mismatchDescription.appendText("enabled was '" + pageObject.isEnabled() + '\'');
            }

            @Override
            protected boolean matchesSafely(PageObject pageObject) {
                return pageObject.isEnabled();
            }

        };
    }

    protected PageObjectMatcher() {
        // utility constructor
    }

}
